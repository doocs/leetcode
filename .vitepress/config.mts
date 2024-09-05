import fs from 'node:fs/promises';
import { resolve } from 'node:path';

import type { DefaultTheme } from 'vitepress';
import { defineConfig } from 'vitepress';

/**
 * 判断路径目标是否为文件夹
 * @param {string} path
 * @returns
 */
const isDir = async (path: string) => {
    try {
        const stat = await fs.stat(path);
        return stat.isDirectory();
    } catch (error) {
        return false;
    }
};

// 获取根目录
const rootPath = resolve(import.meta.dirname, '../');

// TODO 对于 solution，需要递归读取
// const questions = ['lcci', 'lcof', 'lcof2', 'lcp', 'solution'];
// FIXME lcof 较为特殊，似乎中文名加上空格，导致服务崩溃
// const questions = ['lcci', 'lcof', 'lcof2', 'lcp'];
const questions = ['lcci', 'lcof2', 'lcp'];
const items: Record<string, DefaultTheme.Sidebar> = {};
const rewrites: Record<string, string> = {};

for (const question of questions) {
    const files = await fs.readdir(resolve(rootPath, question));
    items[question] = [];
    for (const file of files) {
        if (!(await isDir(resolve(rootPath, question, file)))) {
            continue;
        }
        items[question].push({ text: file, link: `${question}/${file}/README.md` });
        rewrites[`${question}/${file}/README.md`] = `${question}/${file}`;
    }
}

// https://vitepress.dev/reference/site-config
export default defineConfig({
    title: 'LeetCode Wiki',
    description: 'LeetCode、《剑指 Offer（第 2 版）》、《程序员面试金典（第 6 版）》题解',
    locales: {
        root: {
            label: '中文',
            lang: 'cn',
        },
        en: {
            label: 'English',
            lang: 'en-US',
            link: '/en',
        },
    },
    markdown: {
        math: true,
    },
    themeConfig: {
        // https://vitepress.dev/reference/default-theme-config
        nav: [
            { text: 'Home', link: '/' },
            { text: 'LeetCode', link: '/lc/index' },
            ...questions.map(question => ({ text: question, link: `/${question}/README.md` })),
        ],
        socialLinks: [{ icon: 'github', link: 'https://github.com/doocs/leetcode' }],
        sidebar: questions.reduce((r, question) => {
            return {
                ...r,
                [`/${question}/`]: [
                    {
                        text: question,
                        items: items[question],
                    },
                ],
            };
        }, {}),
    },
    rewrites,
});
