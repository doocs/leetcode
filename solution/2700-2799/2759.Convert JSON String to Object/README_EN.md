---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2759.Convert%20JSON%20String%20to%20Object/README_EN.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2759. Convert JSON String to Object 🔒](https://leetcode.com/problems/convert-json-string-to-object)

[中文文档](/solution/2700-2799/2759.Convert%20JSON%20String%20to%20Object/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>str</code>, return parsed JSON&nbsp;<code>parsedStr</code>.&nbsp;You may assume the <code>str</code>&nbsp;is a valid JSON string hence it only includes strings, numbers, arrays, objects, booleans, and null. <code>str</code>&nbsp;will not include invisible characters and escape characters.&nbsp;</p>

<p>Please solve it without using the built-in&nbsp;<code>JSON.parse</code>&nbsp;method.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> str = &#39;{&quot;a&quot;:2,&quot;b&quot;:[1,2,3]}&#39;
<strong>Output:</strong> {&quot;a&quot;:2,&quot;b&quot;:[1,2,3]}
<strong>Explanation:</strong>&nbsp;Returns the object represented by the JSON string.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> str = &#39;true&#39;
<strong>Output:</strong> true
<strong>Explanation:</strong> Primitive types are valid JSON.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> str = &#39;[1,5,&quot;false&quot;,{&quot;a&quot;:2}]&#39;
<strong>Output:</strong> [1,5,&quot;false&quot;,{&quot;a&quot;:2}]
<strong>Explanation:</strong> Returns the array represented by the JSON string.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>str</code> is a valid JSON string</li>
	<li><code>1 &lt;= str.length &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### TypeScript

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
