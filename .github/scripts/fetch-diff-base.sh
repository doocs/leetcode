#!/usr/bin/env bash
set -euo pipefail
if [ "${GITHUB_EVENT_NAME}" = "pull_request" ]; then
  git fetch --no-tags --depth=1 origin "${PR_BASE_SHA}"
  exit 0
fi
before="${GIT_BEFORE:-}"
if [ -z "$before" ] || [[ "$before" =~ ^0+$ ]]; then
  exit 0
fi
git fetch --no-tags --depth=1 origin "$before"
