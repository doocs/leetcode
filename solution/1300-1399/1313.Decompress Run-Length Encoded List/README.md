# [1313. 解压缩编码列表](https://leetcode.cn/problems/decompress-run-length-encoded-list)

[English Version](/solution/1300-1399/1313.Decompress%20Run-Length%20Encoded%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个以行程长度编码压缩的整数列表 <code>nums</code> 。</p>

<p>考虑每对相邻的两个元素 <code>[freq, val] = [nums[2*i], nums[2*i+1]]</code> （其中 <code>i >= 0</code> ），每一对都表示解压后子列表中有 <code>freq</code> 个值为 <code>val</code> 的元素，你需要从左到右连接所有子列表以生成解压后的列表。</p>

<p>请你返回解压后的列表。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>[2,4,4,4]
<strong>解释：</strong>第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2,3]
<strong>输出：</strong>[1,3,3]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= nums.length <= 100</code></li>
	<li><code>nums.length % 2 == 0</code></li>
	<li><code>1 <= nums[i] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def decompressRLElist(self, nums: List[int]) -> List[int]:
        res = []
        for i in range(1, len(nums), 2):
            res.extend([nums[i]] * nums[i - 1])
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] decompressRLElist(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i += 2) {
            n += nums[i];
        }
        int[] res = new int[n];
        for (int i = 1, k = 0; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i - 1]; ++j) {
                res[k++] = nums[i];
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> decompressRLElist(vector<int>& nums) {
        vector<int> res;
        for (int i = 1; i < nums.size(); i += 2) {
            for (int j = 0; j < nums[i - 1]; ++j) {
                res.push_back(nums[i]);
            }
        }
        return res;
    }
};
```

### **Go**

```go
func decompressRLElist(nums []int) []int {
	var res []int
	for i := 1; i < len(nums); i += 2 {
		for j := 0; j < nums[i-1]; j++ {
			res = append(res, nums[i])
		}
	}
	return res
}
```

### **TypeScript**

```ts
function decompressRLElist(nums: number[]): number[] {
    let n = nums.length >> 1;
    let ans = [];
    for (let i = 0; i < n; i++) {
        let freq = nums[2 * i],
            val = nums[2 * i + 1];
        ans.push(...new Array(freq).fill(val));
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn decompress_rl_elist(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len() >> 1;
        let mut ans = Vec::new();
        for i in 0..n {
            for _ in 0..nums[2 * i] {
                ans.push(nums[2 * i + 1]);
            }
        }
        ans
    }
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int *decompressRLElist(int *nums, int numsSize, int *returnSize) {
    int size = 0;
    for (int i = 0; i < numsSize; i += 2) {
        size += nums[i];
    }
    int *ans = malloc(size * sizeof(int));
    for (int i = 0, j = 0; j < numsSize; j += 2) {
        for (int k = 0; k < nums[j]; k++) {
            ans[i++] = nums[j + 1];
        }
    }
    *returnSize = size;
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
