const isEn = () => location.hash.includes('README_EN');
const isRoot = () => ['', '#/', '#/README', '#/README_EN'].includes(location.hash);
const sidebar = () => (isRoot() ? false : isEn() ? 'summary_en.md' : 'summary.md');
const cleanedHtml = html => {
    return html.replace(/<pre>([\s\S]*?)<\/pre>/g, (_, group) => {
        return '<pre>' + group.replace(/<code>([\s\S]*?)<\/code>/g, '$1') + '</pre>';
    });
};
const giscusTheme = () =>
    localStorage.getItem('DARK_LIGHT_THEME') === 'light' ? 'light' : 'noborder_dark';

window.addEventListener('hashchange', () => {
    window.$docsify.loadSidebar = sidebar();
});

window.$docsify = {
    name: 'leetcode',
    repo: 'doocs/leetcode',
    lastModifiedText: {
        '/README_EN': 'Last updated: ',
        '/': '最近更新时间：',
    },
    giscus: {
        repo: 'doocs/leetcode',
        repoId: 'MDEwOlJlcG9zaXRvcnkxNDkwMDEzNjU',
        category: 'Q&A',
        categoryId: 'MDE4OkRpc2N1c3Npb25DYXRlZ29yeTMyMDAxMjk0',
        mapping: 'url',
        reactionsEnabled: '0',
        strict: '1',
        emitMetadata: '0',
        inputPosition: 'bottom',
        theme: giscusTheme(),
        lang: 'zh-CN',
        loading: 'lazy',
    },
    logo: '/images/doocs-leetcode.png',
    search: {
        depth: 2,
        hideOtherSidebarContent: true,
        pathNamespaces: ['/', '/solution', '/lcof', '/lcof2', '/lcci', '/lcs', '/lcp', '/basic'],
    },
    loadSidebar: sidebar(),
    auto2top: true,
    subMaxLevel: 2,
    alias: {
        '/lcs/.*/summary.md': '/lcs/summary.md',
        '/lcp/.*/summary.md': '/lcp/summary.md',
        '/lcci/.*/summary.md': '/lcci/summary.md',
        '/lcof/.*/summary.md': '/lcof/summary.md',
        '/lcof2/.*/summary.md': '/lcof2/summary.md',
        '/solution/.*/summary.md': '/solution/summary.md',
        '/basic/.*/summary.md': '/basic/summary.md',

        '/lcs/.*/summary_en.md': '/lcs/summary_en.md',
        '/lcp/.*/summary_en.md': '/lcp/summary_en.md',
        '/lcci/.*/summary_en.md': '/lcci/summary_en.md',
        '/lcof/.*/summary_en.md': '/lcof/summary_en.md',
        '/lcof2/.*/summary_en.md': '/lcof2/summary_en.md',
        '/solution/.*/summary_en.md': '/solution/summary_en.md',
        '/basic/.*/summary_en.md': '/basic/summary_en.md',
    },
    contributors: {
        repo: 'doocs/leetcode',
        ignores: [
            '/README.md',
            '/README_EN.md',
            '/solution/README.md',
            '/solution/README_EN.md',
            '/summary.md',
        ],
        image: {
            margin: '0.2em',
            isRound: true,
        },
    },
    darklightTheme: {
        defaultTheme: 'light',
        siteFont: 'Source Sans Pro,Helvetica Neue,Arial,sans-serif',
        codeFontFamily: 'Roboto Mono, Monaco, courier, monospace',
        bodyFontSize: '15px',
        dark: {
            background: '#191919',
            highlightColor: '#e96900',
            codeBackgroundColor: '#202020',
            codeTextColor: '#b4b4b4',
        },
        light: {
            highlightColor: '#e96900',
        },
    },
    pagination: {
        previousText: {
            '/README_EN': 'PREVIOUS',
            '/': '上一题',
        },
        nextText: {
            '/README_EN': 'NEXT',
            '/': '下一题',
        },
        crossChapter: true,
        crossChapterText: true,
    },
    tabs: {
        persist: true,
        sync: true,
        theme: 'classic',
        tabComments: true,
        tabHeadings: true,
    },
    plugins: [
        (hook, vm) => {
            hook.beforeEach(html => {
                const { file } = vm.route;
                const isUserContent = /githubusercontent\.com/.test(file);
                const url = isUserContent
                    ? file
                          .replace('raw.githubusercontent.com', 'github.com')
                          .replace(/\/main/, '/blob/main')
                    : `https://github.com/doocs/leetcode/blob/main/${file}`;

                const github = `[GitHub](${url})`;
                const gitee = `[Gitee](${url.replace('github', 'gitee')})`;
                html = cleanedHtml(html);
                const editHtml = isEn()
                    ? `:memo: Edit on ${github} / ${gitee}\n`
                    : `:memo: 在 ${github} / ${gitee} 编辑\n`;
                return editHtml + html;
            });

            hook.afterEach(html => {
                const copyright = isEn() ? '. All Rights Reserved' : ' 版权所有';
                const currentYear = new Date().getFullYear();
                const footer = `<footer>Copyright © 2018-${currentYear} <a href="https://github.com/doocs" target="_blank">Doocs</a>${copyright}</footer>`;
                return html + footer;
            });
            hook.doneEach(() => {
                document.getElementById('docsify-darklight-theme').addEventListener('click', () => {
                    const theme = giscusTheme();
                    const frame = document.querySelector('.giscus-frame');
                    frame.contentWindow.postMessage(
                        { giscus: { setConfig: { theme } } },
                        'https://giscus.app',
                    );
                });
            });
        },
    ],
};
