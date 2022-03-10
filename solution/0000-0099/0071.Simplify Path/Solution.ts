function simplifyPath(path: string): string {
    // 添加辅助斜线
    path += '/';

    const stack = [];
    let str = '';
    for (let i = 1; i < path.length; i++) {
        const c = path[i];
        if (c === '/') {
            if (str !== '' && str !== '.') {
                if (str === '..') {
                    if (stack.length !== 0) {
                        stack.pop();
                    }
                } else {
                    stack.push(str);
                }
            }
            str = '';
        } else {
            str += c;
        }
    }

    return '/' + stack.join('/');
}
