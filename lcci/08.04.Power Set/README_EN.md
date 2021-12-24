# [08.04. Power Set](https://leetcode-cn.com/problems/power-set-lcci)

[中文文档](/lcci/08.04.Power%20Set/README.md)

## Description

<p>Write a method to return all subsets of a set. The elements in a set are&nbsp;pairwise distinct.</p>

<p>Note: The result set should not contain duplicated subsets.</p>

<p><strong>Example:</strong></p>

<pre>

<strong> Input</strong>:  nums = [1,2,3]

<strong> Output</strong>: 

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

## Solutions

Backtracking

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

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
