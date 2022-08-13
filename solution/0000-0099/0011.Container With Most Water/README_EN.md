# [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water)

[中文文档](/solution/0000-0099/0011.Container%20With%20Most%20Water/README.md)

## Description

<p>You are given an integer array <code>height</code> of length <code>n</code>. There are <code>n</code> vertical lines drawn such that the two endpoints of the <code>i<sup>th</sup></code> line are <code>(i, 0)</code> and <code>(i, height[i])</code>.</p>

<p>Find two lines that together with the x-axis form a container, such that the container contains the most water.</p>

<p>Return <em>the maximum amount of water a container can store</em>.</p>

<p><strong>Notice</strong> that you may not slant the container.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0011.Container%20With%20Most%20Water/images/question_11.jpg" style="width: 600px; height: 287px;" />
<pre>
<strong>Input:</strong> height = [1,8,6,2,5,4,8,3,7]
<strong>Output:</strong> 49
<strong>Explanation:</strong> The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> height = [1,1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxArea(self, height: List[int]) -> int:
        i, j = 0, len(height) - 1
        res = 0
        while i < j:
            t = (j - i) * min(height[i], height[j])
            res = max(res, t)
            if height[i] < height[j]:
                i += 1
            else:
                j -= 1
        return res
```

### **Java**

```java
class Solution {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int res = 0;
        while (i < j) {
            int t = (j - i) * Math.min(height[i], height[j]);
            res = Math.max(res, t);
            if (height[i] < height[j]) ++i;
            else --j;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxArea(vector<int>& height) {
        int i = 0, j = height.size() - 1;
        int res = 0;
        while (i < j) {
            int t = (j - i) * min(height[i], height[j]);
            res = max(res, t);
            if (height[i] < height[j])
                ++i;
            else
                --j;
        }
        return res;
    }
};
```

### **Go**

```go
func maxArea(height []int) int {
    i, j := 0, len(height) - 1
    res := 0
    for i != j {
        t := (j - i) * min(height[i], height[j])
        res = max(res, t)
        if height[i] < height[j] {
            i++
        } else {
            j--
        }
    }
    return res
}

func min(a, b int) int {
    if a > b {
        return b
    }
    return a
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} height
 * @return {number}
 */
var maxArea = function (height) {
    let i = 0,
        j = height.length - 1;
    let res = 0;
    while (i < j) {
        const t = (j - i) * Math.min(height[i], height[j]);
        res = Math.max(res, t);
        if (height[i] < height[j]) ++i;
        else --j;
    }
    return res;
};
```

### **TypeScript**

```ts
function maxArea(height: number[]): number {
    const n = height.length;
    let res = 0;
    for (let i = 0; i < n - 1; i++) {
        for (let j = n - 1; j >= 0; j--) {
            if (height[i] * (j - i) < res) {
                break;
            }
            res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_area(height: Vec<i32>) -> i32 {
        let mut i = 0;
        let mut j = height.len() - 1;
        let mut res = 0;
        while i < j {
            res = res.max(height[i].min(height[j]) * (j - i) as i32);
            if height[i] <= height[j] {
                i += 1;
            } else {
                j -= 1;
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
