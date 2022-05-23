# [2075. 解码斜向换位密码](https://leetcode.cn/problems/decode-the-slanted-ciphertext)

[English Version](/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>字符串 <code>originalText</code> 使用 <strong>斜向换位密码</strong> ，经由 <strong>行数固定</strong> 为 <code>rows</code> 的矩阵辅助，加密得到一个字符串 <code>encodedText</code> 。</p>

<p><code>originalText</code> 先按从左上到右下的方式放置到矩阵中。</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/images/exa11.png" style="width: 300px; height: 185px;" />
<p>先填充蓝色单元格，接着是红色单元格，然后是黄色单元格，以此类推，直到到达 <code>originalText</code> 末尾。箭头指示顺序即为单元格填充顺序。所有空单元格用 <code>' '</code> 进行填充。矩阵的列数需满足：用 <code>originalText</code> 填充之后，最右侧列 <strong>不为空</strong> 。</p>

<p>接着按行将字符附加到矩阵中，构造&nbsp;<code>encodedText</code> 。</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/images/exa12.png" style="width: 300px; height: 200px;" />
<p>先把蓝色单元格中的字符附加到 <code>encodedText</code> 中，接着是红色单元格，最后是黄色单元格。箭头指示单元格访问顺序。</p>

<p>例如，如果 <code>originalText = "cipher"</code> 且 <code>rows = 3</code> ，那么我们可以按下述方法将其编码：</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/images/desc2.png" style="width: 281px; height: 211px;" />
<p>蓝色箭头标识 <code>originalText</code> 是如何放入矩阵中的，红色箭头标识形成 <code>encodedText</code> 的顺序。在上述例子中，<code>encodedText = "ch&nbsp; &nbsp;ie&nbsp; &nbsp;pr"</code> 。</p>

<p>给你编码后的字符串 <code>encodedText</code> 和矩阵的行数 <code>rows</code> ，返回源字符串 <code>originalText</code> 。</p>

<p><strong>注意：</strong><code>originalText</code> <strong>不</strong> 含任何尾随空格 <code>' '</code> 。生成的测试用例满足 <strong>仅存在一个</strong> 可能的 <code>originalText</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>encodedText = "ch   ie   pr", rows = 3
<strong>输出：</strong>"cipher"
<strong>解释：</strong>此示例与问题描述中的例子相同。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/images/exam1.png" style="width: 250px; height: 168px;" /></p>

<pre>
<strong>输入：</strong>encodedText = "iveo    eed   l te   olc", rows = 4
<strong>输出：</strong>"i love leetcode"
<strong>解释：</strong>上图标识用于编码 originalText 的矩阵。 
蓝色箭头展示如何从 encodedText 找到 originalText 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/images/eg2.png" style="width: 300px; height: 51px;" /></p>

<pre>
<strong>输入：</strong>encodedText = "coding", rows = 1
<strong>输出：</strong>"coding"
<strong>解释：</strong>由于只有 1 行，所以 originalText 和 encodedText 是相同的。
</pre>

<p><strong>示例 4：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/images/exam3.png" style="width: 150px; height: 101px;" />
<pre>
<strong>输入：</strong>encodedText = " b  ac", rows = 2
<strong>输出：</strong>" abc"
<strong>解释：</strong>originalText 不能含尾随空格，但它可能会有一个或者多个前置空格。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= encodedText.length &lt;= 10<sup>6</sup></code></li>
	<li><code>encodedText</code> 仅由小写英文字母和 <code>' '</code> 组成</li>
	<li><code>encodedText</code> 是对某个 <strong>不含</strong> 尾随空格的 <code>originalText</code> 的一个有效编码</li>
	<li><code>1 &lt;= rows &lt;= 1000</code></li>
	<li>生成的测试用例满足 <strong>仅存在一个</strong> 可能的 <code>originalText</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def decodeCiphertext(self, encodedText: str, rows: int) -> str:
        ans = []
        cols = len(encodedText) // rows
        for j in range(cols):
            x, y = 0, j
            while x < rows and y < cols:
                ans.append(encodedText[x * cols + y])
                x, y = x + 1, y + 1
        return ''.join(ans).rstrip()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        StringBuilder ans = new StringBuilder();
        int cols = encodedText.length() / rows;
        for (int j = 0; j < cols; ++j) {
            for (int x = 0, y = j; x < rows && y < cols; ++x, ++y) {
                ans.append(encodedText.charAt(x * cols + y));
            }
        }
        while (ans.length() > 0 && ans.charAt(ans.length() - 1) == ' ') {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.toString();
    }
}
```

### **TypeScript**

```ts
function decodeCiphertext(encodedText: string, rows: number): string {
    const cols = Math.ceil(encodedText.length / rows);
    let ans = [];
    for (let k = 0; k <= cols; k++) {
        for (let i = 0, j = k; i < rows && j < cols; i++, j++) {
            ans.push(encodedText.charAt(i * cols + j));
        }
    }
    return ans.join('').trimEnd();
}
```

### **C++**

```cpp
class Solution {
public:
    string decodeCiphertext(string encodedText, int rows) {
        string ans;
        int cols = encodedText.size() / rows;
        for (int j = 0; j < cols; ++j)
            for (int x = 0, y = j; x < rows && y < cols; ++x, ++y)
                ans += encodedText[x * cols + y];
        while (ans.back() == ' ') ans.pop_back();
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
