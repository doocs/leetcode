"""Write per-range sitemap.xml files under lc/NNNN-NNNN/ after the site build."""

from __future__ import annotations

import re
import xml.etree.ElementTree as ET
from collections import defaultdict
from pathlib import Path
from typing import Dict, Iterable, List, Optional, Tuple

SITEMAP_NS = "http://www.sitemaps.org/schemas/sitemap/0.9"
RANGE_SLUG = re.compile(r"^(\d{4})-(\d{4})$")
LC_TAIL = re.compile(r"/lc/([^/]+)/?$")


def parse_sitemap_urls(xml_text: str) -> List[Tuple[str, Optional[str]]]:
    root = ET.fromstring(xml_text)
    urls: List[Tuple[str, Optional[str]]] = []
    for url in root.findall(f"{{{SITEMAP_NS}}}url"):
        loc_el = url.find(f"{{{SITEMAP_NS}}}loc")
        if loc_el is None or not (loc_el.text or "").strip():
            continue
        last_el = url.find(f"{{{SITEMAP_NS}}}lastmod")
        lastmod = (last_el.text or "").strip() if last_el is not None else ""
        urls.append((loc_el.text.strip(), lastmod or None))
    return urls


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


def group_lc_urls(
    urls: Iterable[Tuple[str, Optional[str]]],
) -> Dict[str, List[Tuple[str, Optional[str]]]]:
    buckets: Dict[str, List[Tuple[str, Optional[str]]]] = defaultdict(list)
    for loc, lastmod in urls:
        slug = range_slug_for_loc(loc)
        if slug:
            buckets[slug].append((loc, lastmod))
    for slug, entries in buckets.items():
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


def render_sitemap(entries: List[Tuple[str, Optional[str]]]) -> str:
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


def write_range_sitemaps(site_dir: Path, sitemap_xml: str) -> List[Path]:
    written: List[Path] = []
    for slug, entries in group_lc_urls(parse_sitemap_urls(sitemap_xml)).items():
        dest_dir = site_dir / "lc" / slug
        dest_dir.mkdir(parents=True, exist_ok=True)
        dest = dest_dir / "sitemap.xml"
        dest.write_text(render_sitemap(entries), encoding="utf-8")
        written.append(dest)
    return written


def on_post_build(config):
    site_dir = Path(config["site_dir"])
    sitemap = site_dir / "sitemap.xml"
    if not sitemap.is_file():
        return
    write_range_sitemaps(site_dir, sitemap.read_text(encoding="utf-8"))
