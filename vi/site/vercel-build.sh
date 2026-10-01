#!/usr/bin/env bash
# Build 中文 (/), English (/en/) and Tiếng Việt (/vi/) into site/ (vercel.json).
#
# On Vercel the site URL and repository come from the system environment
# variables; set SITE_URL to override. Local run for a few problems:
#
#   VI_ONLY=1,74,lcci/01.01 bash vi/site/vercel-build.sh
#
# VI_JOBS=1 builds the three sites one after another (less memory).
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
if [ -n "${VI_ONLY:-}" ]; then
  args+=(--only "$VI_ONLY")
fi
python vi/site/prepare.py "${args[@]}"
python -m pip install -q -r "$WORK/requirements.txt"

export VI_STRICT=1 NO_MKDOCS_2_WARNING=1
cd "$WORK"
build() {
  # A separate site dir per language: MkDocs cleans its site dir first.
  mkdocs build -f "mkdocs-site-$1.yml" --site-dir "build-$1" > "build-$1.log" 2>&1
}
failed=0
if [ "${VI_JOBS:-3}" = "1" ]; then
  for lang in zh en vi; do
    build "$lang" || failed=1
  done
else
  pids=()
  for lang in zh en vi; do
    build "$lang" &
    pids+=("$!")
  done
  for pid in "${pids[@]}"; do
    wait "$pid" || failed=1
  done
fi
for lang in zh en vi; do
  echo "== $lang: $(grep -c '^WARNING' "build-$lang.log" || true) warning(s)"
  grep -E '^(WARNING|ERROR)|Traceback|Error' "build-$lang.log" | head -n 20 || true
done
if [ "$failed" -ne 0 ]; then
  tail -n 40 build-*.log
  exit 1
fi

rm -rf "$OUT"
mv build-zh "$OUT"
mv build-en "$OUT/en"
mv build-vi "$OUT/vi"
echo "Built $(find "$OUT" -name index.html | wc -l) pages into $OUT"
