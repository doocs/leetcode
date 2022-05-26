# [845. 数组中的最长山脉](https://leetcode.cn/problems/longest-mountain-in-array)

[English Version](/solution/0800-0899/0845.Longest%20Mountain%20in%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>把符合下列属性的数组 <code>arr</code> 称为 <strong>山脉数组</strong> ：</p>

<ul>
	<li><code>arr.length &gt;= 3</code></li>
	<li>存在下标 <code>i</code>（<code>0 &lt; i &lt; arr.length - 1</code>），满足
	<ul>
		<li><code>arr[0] &lt; arr[1] &lt; ... &lt; arr[i - 1] &lt; arr[i]</code></li>
		<li><code>arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code></li>
	</ul>
	</li>
</ul>

<p>给出一个整数数组 <code>arr</code>，返回最长山脉子数组的长度。如果不存在山脉子数组，返回 <code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [2,1,4,7,3,2,5]
<strong>输出：</strong>5
<strong>解释：</strong>最长的山脉子数组是 [1,4,7,3,2]，长度为 5。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [2,2,2]
<strong>输出：</strong>0
<strong>解释：</strong>不存在山脉子数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你可以仅用一趟扫描解决此问题吗？</li>
	<li>你可以用 <code>O(1)</code> 空间解决此问题吗？</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestMountain(self, arr: List[int]) -> int:
        left, right = 0, 1
        status = -1
        ans = 0
        while right < len(arr):
            if status == -1 or status == 1:
                if arr[right] == arr[right - 1]:
                    status = -1
                if status == -1:
                    if arr[right] > arr[right - 1]:
                        status = 1
                    else:
                        left = right
                if status == 1 and arr[right] < arr[right - 1]:
                    status = 2
            else:
                if arr[right] == arr[right - 1]:
                    status = -1
                    ans = max(ans, right - left)
                    left = right
                elif arr[right] > arr[right - 1]:
                    status = 1
                    ans = max(ans, right - left)
                    left = right - 1
            right += 1
        if status == 2:
            ans = max(right - left, ans)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestMountain(int[] arr) {
        int left = 0, right = 0;
        int ans = 0;
        int status = -1;
        while (++right < arr.length) {
            if (status == -1 || status == 1) {
                if (arr[right] == arr[right - 1]) {
                    status = -1;
                }
                if (status == -1) {
                    if (arr[right] > arr[right - 1]) {
                        status = 1;
                    } else {
                        left = right;
                    }
                }
                if (status == 1 && arr[right] < arr[right - 1]) {
                    status = 2;
                }
            } else {
                if (arr[right] > arr[right - 1]) {
                    status = 1;
                    ans = Math.max(right - left, ans);
                    left = right - 1;
                } else if (arr[right] == arr[right - 1]) {
                    status = -1;
                    ans = Math.max(right - left, ans);
                    left = right;
                }
            }
        }
        if (status == 2) {
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
