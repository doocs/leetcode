#!/usr/bin/env node
/**
 * Run a command with the first available Python launcher.
 * Windows prefers `py`; Unix prefers `python3`.
 */
const { spawnSync } = require('child_process');

const extra = process.argv.slice(2);
const candidates =
    process.platform === 'win32' ? ['py', 'python', 'python3'] : ['python3', 'python', 'py'];

for (const cmd of candidates) {
    const result = spawnSync(cmd, extra, {
        stdio: 'inherit',
        windowsHide: true,
    });
    if (result.error && result.error.code === 'ENOENT') {
        continue;
    }
    process.exit(result.status ?? 1);
}

console.error('No Python interpreter found (tried py, python, python3).');
process.exit(127);
