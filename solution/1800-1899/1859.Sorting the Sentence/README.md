# [1859. 将句子排序](https://leetcode.cn/problems/sorting-the-sentence)

[English Version](/solution/1800-1899/1859.Sorting%20the%20Sentence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个 <strong>句子</strong> 指的是一个序列的单词用单个空格连接起来，且开头和结尾没有任何空格。每个单词都只包含小写或大写英文字母。</p>

<p>我们可以给一个句子添加 <strong>从 1 开始的单词位置索引 </strong>，并且将句子中所有单词 <strong>打乱顺序</strong> 。</p>

<ul>
	<li>比方说，句子 <code>"This is a sentence"</code> 可以被打乱顺序得到 <code>"sentence4 a3 is2 This1"</code> 或者 <code>"is2 sentence4 This1 a3"</code> 。</li>
</ul>

<p>给你一个 <strong>打乱顺序</strong> 的句子 <code>s</code> ，它包含的单词不超过 <code>9</code> 个，请你重新构造并得到原本顺序的句子。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "is2 sentence4 This1 a3"
<b>输出：</b>"This is a sentence"
<b>解释：</b>将 s 中的单词按照初始位置排序，得到 "This1 is2 a3 sentence4" ，然后删除数字。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "Myself2 Me1 I4 and3"
<b>输出：</b>"Me Myself and I"
<b>解释：</b>将 s 中的单词按照初始位置排序，得到 "Me1 Myself2 and3 I4" ，然后删除数字。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= s.length <= 200</code></li>
	<li><code>s</code> 只包含小写和大写英文字母、空格以及从 <code>1</code> 到 <code>9</code> 的数字。</li>
	<li><code>s</code> 中单词数目为 <code>1</code> 到 <code>9</code> 个。</li>
	<li><code>s</code> 中的单词由单个空格分隔。</li>
	<li><code>s</code> 不包含任何前导或者后缀空格。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sortSentence(self, s: str) -> str:
        words = s.split(' ')
        arr = [None] * len(words)
        for word in words:
            idx = int(word[-1]) - 1
            arr[idx] = word[:-1]
        return ' '.join(arr)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String sortSentence(String s) {
        String[] words = s.split(" ");
        String[] arr = new String[words.length];
        for (String word : words) {
            int idx = word.charAt(word.length() - 1) - '0' - 1;
            arr[idx] = word.substring(0, word.length() - 1);
        }
        return String.join(" ", arr);
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string}
 */
var sortSentence = function (s) {
    let words = s.split(' ');
    let n = words.length;
    let res = new Array(n);
    for (let word of words) {
        let key = word.slice(-1);
        let val = word.slice(0, -1);
        res[parseInt(key) - 1] = val;
    }
    return res.join(' ');
};
```

### **...**

```

```

<!-- tabs:end -->
