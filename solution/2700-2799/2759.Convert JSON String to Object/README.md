---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2759.Convert%20JSON%20String%20to%20Object/README.md
---

<!-- problem:start -->

# [2759. 将 JSON 字符串转换为对象 🔒](https://leetcode.cn/problems/convert-json-string-to-object)

[English Version](/solution/2700-2799/2759.Convert%20JSON%20String%20to%20Object/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串 <code>str</code> ，返回 JSON 解析后的 <code>parsedStr</code> 。你可以假设 <code>str</code> 是一个有效的 JSON 字符串，因此它只包含字符串、数字、数组、对象、布尔值和 null。<code>str</code> 不会包含不可见字符和转义字符。</p>

<p>请在不使用内置的 <code>JSON.parse</code> 方法的情况下解决此问题。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>str = '{"a":2,"b":[1,2,3]}'
<b>输出：</b>{"a":2,"b":[1,2,3]}
<b>解释：</b>返回由 JSON 字符串表示的对象。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>str = 'true'
<b>输出：</b>true
<b>解释：</b>原始类型是有效的 JSON。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>str = '[1,5,"false",{"a":2}]'
<b>输出：</b>[1,5,"false",{"a":2}]
<b>解释：</b>返回由 JSON 字符串表示的数组。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>str</code> 是一个有效的 JSON 字符串</li>
	<li><code>1 &lt;= str.length &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```ts
function jsonParse(str: string): any {
    const n = str.length;
    let i = 0;

    const parseTrue = (): boolean => {
        i += 4;
        return true;
    };

    const parseFalse = (): boolean => {
        i += 5;
        return false;
    };

    const parseNull = (): null => {
        i += 4;
        return null;
    };

    const parseNumber = (): number => {
        let s = '';
        while (i < n) {
            const c = str[i];
            if (c === ',' || c === '}' || c === ']') {
                break;
            }
            s += c;
            i++;
        }
        return Number(s);
    };

    const parseArray = (): any[] => {
        const arr: any[] = [];
        i++;
        while (i < n) {
            const c = str[i];
            if (c === ']') {
                i++;
                break;
            }
            if (c === ',') {
                i++;
                continue;
            }
            const value = parseValue();
            arr.push(value);
        }
        return arr;
    };

    const parseString = (): string => {
        let s = '';
        i++;
        while (i < n) {
            const c = str[i];
            if (c === '"') {
                i++;
                break;
            }
            if (c === '\\') {
                i++;
                s += str[i];
            } else {
                s += c;
            }
            i++;
        }
        return s;
    };

    const parseObject = (): any => {
        const obj: any = {};
        i++;
        while (i < n) {
            const c = str[i];
            if (c === '}') {
                i++;
                break;
            }
            if (c === ',') {
                i++;
                continue;
            }
            const key = parseString();
            i++;
            const value = parseValue();
            obj[key] = value;
        }
        return obj;
    };
    const parseValue = (): any => {
        const c = str[i];
        if (c === '{') {
            return parseObject();
        }
        if (c === '[') {
            return parseArray();
        }
        if (c === '"') {
            return parseString();
        }
        if (c === 't') {
            return parseTrue();
        }
        if (c === 'f') {
            return parseFalse();
        }
        if (c === 'n') {
            return parseNull();
        }
        return parseNumber();
    };
    return parseValue();
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
