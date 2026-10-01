#!/usr/bin/env bash
# Build the Tiếng Việt site into site/vi/ (vercel.json outputDirectory: site).
#
# Only the Vietnamese site is built; its 中文 / English links open the upstream
# site (UPSTREAM_SITE, default https://leetcode.doocs.org). On Vercel the site
# URL and repository come from the system environment variables; set SITE_URL
# to override. Local run for a few problems:
#
#   VI_ONLY=1,74,lcci/01.01 bash vi/site/vercel-build.sh
set -euo pipefail

REPO="$(cd "$(dirname "$0")/../.." && pwd)"
WORK="$REPO/.preview/vi-site"
OUT="$REPO/site"
cd "$REPO"

PY=""
for candidate in python3.13 python3.12 python3; do
  if command -v "$candidate" >/dev/null; then
    PY="$candidate"
    break
  fi
done
[ -n "$PY" ] || { echo "python3 not found" >&2; exit 1; }
"$PY" -m venv .preview/venv
# shellcheck disable=SC1091
. .preview/venv/bin/activate
python -m pip install -q --upgrade pip
python -m pip install -q pyyaml

if [ -z "${SITE_URL:-}" ]; then
  if [ -n "${VERCEL_PROJECT_PRODUCTION_URL:-}" ]; then
    SITE_URL="https://$VERCEL_PROJECT_PRODUCTION_URL"
  else
    SITE_URL="http://127.0.0.1:8000"
  fi
fi
SLUG="${VERCEL_GIT_REPO_OWNER:-vandunxg}/${VERCEL_GIT_REPO_SLUG:-leetcode}"

args=(--workdir "$WORK" --site-url "$SITE_URL" --repo "$SLUG")
args+=(--upstream-url "${UPSTREAM_SITE:-https://leetcode.doocs.org}")
if [ -n "${VI_ONLY:-}" ]; then
  args+=(--only "$VI_ONLY")
fi
python vi/site/prepare.py "${args[@]}"
python -m pip install -q -r "$WORK/requirements.txt"

export VI_STRICT=1 NO_MKDOCS_2_WARNING=1
(cd "$WORK" && mkdocs build -f mkdocs-site-vi.yml --site-dir build-vi)

rm -rf "$OUT"
mkdir -p "$OUT"
mv "$WORK/build-vi" "$OUT/vi"
# Vercel serves /404.html for every missing path; the vi one uses /vi/ assets.
cp "$OUT/vi/404.html" "$OUT/404.html"
echo "Built $(find "$OUT/vi" -name index.html | wc -l) pages into $OUT/vi"
