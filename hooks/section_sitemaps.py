"""Write sitemap.xml next to every built page after the MkDocs build."""

from __future__ import annotations

import re
import xml.etree.ElementTree as ET
from collections import defaultdict
from pathlib import Path
from typing import Dict, Iterable, List, Optional, Tuple
from urllib.parse import urlparse

SITEMAP_NS = "http://www.sitemaps.org/schemas/sitemap/0.9"
RANGE_SLUG = re.compile(r"^(\d{4})-(\d{4})$")
LC_TAIL = re.compile(r"/lc/([^/]+)/?$")

UrlEntry = Tuple[str, Optional[str]]


def parse_sitemap_urls(xml_text: str) -> List[UrlEntry]:
    root = ET.fromstring(xml_text)
    urls: List[UrlEntry] = []
    for url in root.findall(f"{{{SITEMAP_NS}}}url"):
        loc_el = url.find(f"{{{SITEMAP_NS}}}loc")
        if loc_el is None or not (loc_el.text or "").strip():
            continue
        last_el = url.find(f"{{{SITEMAP_NS}}}lastmod")
        lastmod = (last_el.text or "").strip() if last_el is not None else ""
        urls.append((loc_el.text.strip(), lastmod or None))
    return urls


def loc_to_rel_dir(loc: str, site_url: str) -> Optional[str]:
    loc = loc.rstrip("/")
    site_url = site_url.rstrip("/")
    if site_url and loc == site_url:
        return None
    if site_url and loc.startswith(site_url + "/"):
        rel = loc[len(site_url) + 1 :]
        return rel or None
    path = urlparse(loc).path.strip("/")
    return path or None


def range_slug_for_loc(loc: str) -> Optional[str]:
    match = LC_TAIL.search(loc)
    if not match:
        return None
    slug = match.group(1)
    if RANGE_SLUG.fullmatch(slug):
        return slug
    first = slug.split(".")[0]
    if not first.isdigit():
        return None
    start = int(first) // 100 * 100
    return f"{start:04d}-{start + 99:04d}"


def group_lc_urls(urls: Iterable[UrlEntry]) -> Dict[str, List[UrlEntry]]:
    buckets: Dict[str, List[UrlEntry]] = defaultdict(list)
    for loc, lastmod in urls:
        slug = range_slug_for_loc(loc)
        if slug:
            buckets[slug].append((loc, lastmod))
    for entries in buckets.values():
        entries.sort(
            key=lambda item: (
                (
                    0
                    if RANGE_SLUG.fullmatch(item[0].rstrip("/").rsplit("/", 1)[-1])
                    else 1
                ),
                item[0],
            )
        )
    return buckets


def group_section_urls(
    urls: Iterable[UrlEntry], site_url: str
) -> Dict[str, List[UrlEntry]]:
    buckets: Dict[str, List[UrlEntry]] = defaultdict(list)
    for loc, lastmod in urls:
        rel = loc_to_rel_dir(loc, site_url)
        if not rel:
            continue
        section = rel.split("/", 1)[0]
        buckets[section].append((loc, lastmod))
    for entries in buckets.values():
        entries.sort(key=lambda item: item[0])
    return buckets


def render_sitemap(entries: List[UrlEntry]) -> str:
    ET.register_namespace("", SITEMAP_NS)
    urlset = ET.Element(f"{{{SITEMAP_NS}}}urlset")
    for loc, lastmod in entries:
        url = ET.SubElement(urlset, f"{{{SITEMAP_NS}}}url")
        loc_el = ET.SubElement(url, f"{{{SITEMAP_NS}}}loc")
        loc_el.text = loc
        if lastmod:
            last_el = ET.SubElement(url, f"{{{SITEMAP_NS}}}lastmod")
            last_el.text = lastmod
    xml = ET.tostring(urlset, encoding="utf-8", xml_declaration=True)
    text = xml.decode("utf-8")
    return text if text.endswith("\n") else text + "\n"


def _write(dest: Path, entries: List[UrlEntry]) -> Path:
    dest.parent.mkdir(parents=True, exist_ok=True)
    dest.write_text(render_sitemap(entries), encoding="utf-8")
    return dest


def write_page_sitemaps(
    site_dir: Path, urls: Iterable[UrlEntry], site_url: str
) -> List[Path]:
    written: List[Path] = []
    for loc, lastmod in urls:
        rel = loc_to_rel_dir(loc, site_url)
        if not rel:
            continue
        written.append(_write(site_dir / rel / "sitemap.xml", [(loc, lastmod)]))
    return written


def write_section_sitemaps(
    site_dir: Path, urls: Iterable[UrlEntry], site_url: str
) -> List[Path]:
    written: List[Path] = []
    for section, entries in group_section_urls(urls, site_url).items():
        written.append(_write(site_dir / section / "sitemap.xml", entries))
    return written


def write_range_sitemaps(site_dir: Path, urls: Iterable[UrlEntry]) -> List[Path]:
    written: List[Path] = []
    for slug, entries in group_lc_urls(urls).items():
        written.append(_write(site_dir / "lc" / slug / "sitemap.xml", entries))
    return written


def write_all_sitemaps(site_dir: Path, sitemap_xml: str, site_url: str) -> List[Path]:
    urls = parse_sitemap_urls(sitemap_xml)
    written = write_page_sitemaps(site_dir, urls, site_url)
    written.extend(write_section_sitemaps(site_dir, urls, site_url))
    written.extend(write_range_sitemaps(site_dir, urls))
    return written


def on_post_build(config):
    site_dir = Path(config["site_dir"])
    site_url = str(config.get("site_url") or "").rstrip("/")
    sitemap = site_dir / "sitemap.xml"
    if not sitemap.is_file():
        return
    write_all_sitemaps(site_dir, sitemap.read_text(encoding="utf-8"), site_url)
