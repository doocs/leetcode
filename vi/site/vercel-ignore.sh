#!/usr/bin/env bash
# Vercel "Ignored Build Step" (vercel.json ignoreCommand):
# exit 0 skips the deployment, exit 1 builds it.
# Only main is deployed, and only when an input of the site changed.

if [ "${VERCEL_GIT_COMMIT_REF:-}" != "main" ]; then
  echo "Skip: ${VERCEL_GIT_COMMIT_REF:-unknown branch} is not main."
  exit 0
fi

if git diff --quiet HEAD^ HEAD -- \
  vi solution lcci translation/state/units vercel.json; then
  echo "Skip: no site input changed in this commit."
  exit 0
fi

echo "Build: site inputs changed (or the previous commit is unavailable)."
exit 1
