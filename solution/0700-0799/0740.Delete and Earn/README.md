# [740. 删除与获得点数](https://leetcode-cn.com/problems/delete-and-earn)

[English Version](/solution/0700-0799/0740.Delete%20and%20Earn/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一个整数数组&nbsp;<code>nums</code>&nbsp;，你可以对它进行一些操作。</p>

<p>每次操作中，选择任意一个&nbsp;<code>nums[i]</code>&nbsp;，删除它并获得&nbsp;<code>nums[i]</code>&nbsp;的点数。之后，你必须删除<strong>每个</strong>等于&nbsp;<code>nums[i] - 1</code>&nbsp;或&nbsp;<code>nums[i] + 1</code>&nbsp;的元素。</p>

<p>开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [3, 4, 2]
<strong>输出:</strong> 6
<strong>解释:</strong> 
删除 4 来获得 4 个点数，因此 3 也被删除。
之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> nums = [2, 2, 3, 3, 3, 4]
<strong>输出:</strong> 9
<strong>解释:</strong> 
删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。
</pre>

<p><strong>注意:</strong></p>

<ul>
	<li><code>nums</code>的长度最大为<code>20000</code>。</li>
	<li>每个整数<code>nums[i]</code>的大小都在<code>[1, 10000]</code>范围内。</li>
</ul>



## 解法
<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->
核心思路: **一个数字要么不选，要么全选**

首先计算出每个数字的总和sums，并维护两个dp数组：select和nonSelect

- sums[i]代表值为i的元素总和
- select[i]代表如果选数字i，从0处理到i的最大和
- nonSelect[i]代表如果不选数字i，从0处理到i的最大和

那么我们有以下逻辑：

- 如果选i，那么i-1肯定不能选；
- 如果不选i，那么i-1选不选都可以，因此我们选择其中较大的选法

``` java
select[i] = nonSelect[i-1] + sums[i];
nonSelect[i] = Math.max(select[i-1], nonSelect[i-1]);
```

### **Python3**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] sums = new int[10010];
        for (int x : nums) {
            sums[x] += x;
        }
        int[] select = new int[10010];
        int[] nonSelect = new int[10010];
        for (int i = 1; i <= 10000; i++) {
            select[i] = nonSelect[i - 1] + sums[i];
            nonSelect[i] = Math.max(select[i - 1], nonSelect[i - 1]);
        }

        return Math.max(select[10000], nonSelect[10000]);
    }
}
```

### **...**

```

```

<!-- tabs:end -->