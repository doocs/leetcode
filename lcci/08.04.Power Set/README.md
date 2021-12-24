# [面试题 08.04. 幂集](https://leetcode-cn.com/problems/power-set-lcci)

[English Version](/lcci/08.04.Power%20Set/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>幂集。编写一种方法，返回某集合的所有子集。集合中<strong>不包含重复的元素</strong>。</p>

<p>说明：解集不能包含重复的子集。</p>

<p><strong>示例:</strong></p>

<pre><strong> 输入</strong>： nums = [1,2,3]
<strong> 输出</strong>：
[
  [3],
&nbsp; [1],
&nbsp; [2],
&nbsp; [1,2,3],
&nbsp; [1,3],
&nbsp; [2,3],
&nbsp; [1,2],
&nbsp; []
]
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

回溯法

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var subsets = function (nums) {
    let prev = [];
    let res = [];
    dfs(nums, 0, prev, res);
    return res;
};

function dfs(nums, depth, prev, res) {
    res.push(prev.slice());
    for (let i = depth; i < nums.length; i++) {
        prev.push(nums[i]);
        depth++;
        dfs(nums, depth, prev, res);
        prev.pop();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
