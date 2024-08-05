import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "LeetCode Wiki",
  description: "LeetCode、《剑指 Offer（第 2 版）》、《程序员面试金典（第 6 版）》题解",
  locales: {
    root: {
      label: '中文',
      lang: 'cn'
    },
    en: {
      label: 'English',
      lang: 'en-US',
      link: '/en'
    }
  },
  markdown: {
    math: true
  },
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: 'Home', link: '/' },
      { text: 'LeetCode', link: '/lc/index' }
    ],
    socialLinks: [
      { icon: 'github', link: 'https://github.com/doocs/leetcode' }
    ],
    sidebar: [
      {
        text: 'LeetCode',
        items: [
          { text: '0001.Two Sum', link: '/lc/1.md' },
          { text: '0002.Add Two Numbers', link: '/lc/2.md'},
          { text: '0003.Longest Substring Without Repeating Characters', link: '/lc/3.md'},
        ]
      }
    ]
  },
  rewrites: {
    'solution/README.md': 'lc/index.md',
    'solution/README_EN.md': 'en/lc/index.md',
    'solution/0000-0099/0001.Two Sum/README.md': 'lc/1.md',
    'solution/0000-0099/0001.Two Sum/README_EN.md': 'en/lc/1.md',
  }
})
