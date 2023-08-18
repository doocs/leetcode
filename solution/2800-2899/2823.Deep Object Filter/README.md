# [2823. Deep Object Filter](https://leetcode.cn/problems/deep-object-filter)

[English Version](/solution/2800-2899/2823.Deep%20Object%20Filter/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Given an object <code>obj</code> and a function <code>fn</code>, return a filtered object <code>filteredObject</code>.&nbsp;</p>

<p>Function <code>deepFilter</code>&nbsp;should perform a deep filter operation on the object <code>obj</code>. The deep filter operation should remove properties for which the output of the filter function <code>fn</code> is <code>false</code>, as well as any empty objects or arrays that remain after the keys have been removed.</p>

<p>If the deep filter operation results in an empty object or array, with no remaining properties, <code>deepFilter</code> should return <code>undefined</code> to indicate that there is no valid data left in the <code>filteredObject</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
obj = [-5, -4, -3, -2, -1, 0, 1], 
fn = (x) =&gt; x &gt; 0
<strong>Output:</strong> [1]
<strong>Explanation:</strong> All values that were not greater than 0 were removed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
obj = {&quot;a&quot;: 1, &quot;b&quot;: &quot;2&quot;, &quot;c&quot;: 3, &quot;d&quot;: &quot;4&quot;, &quot;e&quot;: 5, &quot;f&quot;: 6, &quot;g&quot;: {&quot;a&quot;: 1}}, 
fn = (x) =&gt; typeof x === &quot;string&quot;
<strong>Output:</strong> {&quot;b&quot;:&quot;2&quot;,&quot;d&quot;:&quot;4&quot;}
<strong>Explanation:</strong> All keys with values that were not a string were removed. When the object keys were removed during the filtering process, any resulting empty objects were also removed.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
obj = [-1, [-1, -1, 5, -1, 10], -1, [-1], [-5]], 
fn = (x) =&gt; x &gt; 0
<strong>Output:</strong> [[5,10]]
<strong>Explanation:</strong> All values that were not greater than 0 were removed. When the values were removed during the filtering process, any resulting empty arrays were also removed.</pre>

<p><strong class="example">Example 4:</strong></p>

<pre>
<strong>Input:</strong> 
obj = [[[[5]]]], 
fn = (x) =&gt; Array.isArray(x)
<strong>Output:</strong> undefined
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>fn</code> is a function that returns a boolean value</li>
	<li><code>obj</code> is a valid JSON object</li>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 10**5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

我们先判断当前对象是否为数组，如果是数组，我们就对数组中的每一个元素进行递归调用，然后过滤掉返回值为 `undefined` 的元素，最后返回过滤后的数组。

如果当前对象不是数组，我们就判断当前对象是否为对象，如果是对象，我们就对对象中的每一个属性值进行递归调用，然后过滤掉返回值为 `undefined` 的属性值，最后返回过滤后的对象。

如果当前对象既不是数组也不是对象，我们就直接返回 `fn(obj) ? obj : undefined`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为对象中的元素个数。

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function deepFilter(
    obj: Record<string, any>,
    fn: Function,
): Record<string, any> | undefined {
    const dfs = (data: any): any => {
        if (Array.isArray(data)) {
            const res = data.map(dfs).filter((item: any) => item !== undefined);
            return res.length > 0 ? res : undefined;
        }
        if (typeof data === 'object' && data !== null) {
            const res: Record<string, any> = {};
            for (const key in data) {
                if (data.hasOwnProperty(key)) {
                    const filteredValue = dfs(data[key]);
                    if (filteredValue !== undefined) {
                        res[key] = filteredValue;
                    }
                }
            }
            return Object.keys(res).length > 0 ? res : undefined;
        }
        return fn(data) ? data : undefined;
    };

    return dfs(obj);
}
```

<!-- tabs:end -->
