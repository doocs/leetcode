#!/usr/bin/env node
/**
 * Run a command with the first available Python 3 interpreter.
 * Windows prefers `py -3`; Unix prefers `python3`.
 */
const { spawnSync } = require('child_process');

const extra = process.argv.slice(2);
const candidates =
    process.platform === 'win32'
        ? [
              ['py', ['-3']],
              ['python', []],
              ['python3', []],
          ]
        : [
              ['python3', []],
              ['python', []],
              ['py', ['-3']],
          ];

const tried = [];
for (const [cmd, prefix] of candidates) {
    tried.push(prefix.length ? `${cmd} ${prefix.join(' ')}` : cmd);
    const result = spawnSync(cmd, [...prefix, ...extra], {
        stdio: 'inherit',
        windowsHide: true,
    });
    if (result.error) {
        if (result.error.code === 'ENOENT') {
            continue;
        }
        console.error(`${cmd}: ${result.error.message}`);
        process.exit(1);
    }
    process.exit(result.status ?? 1);
}

console.error(`No Python 3 interpreter found (tried ${tried.join(', ')}).`);
process.exit(127);
