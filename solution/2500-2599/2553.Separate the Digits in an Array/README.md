# [2553. 分割数组中数字的数位](https://leetcode.cn/problems/separate-the-digits-in-an-array)

[English Version](/solution/2500-2599/2553.Separate%20the%20Digits%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组&nbsp;<code>nums</code>&nbsp;，请你返回一个数组<em>&nbsp;</em><code>answer</code> ，你需要将&nbsp;<code>nums</code>&nbsp;中每个整数进行数位分割后，按照&nbsp;<code>nums</code>&nbsp;中出现的&nbsp;<strong>相同顺序</strong>&nbsp;放入答案数组中。</p>

<p>对一个整数进行数位分割，指的是将整数各个数位按原本出现的顺序排列成数组。</p>

<ul>
	<li>比方说，整数&nbsp;<code>10921</code>&nbsp;，分割它的各个数位得到&nbsp;<code>[1,0,9,2,1]</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [13,25,83,77]
<b>输出：</b>[1,3,2,5,8,3,7,7]
<b>解释：</b>
- 分割 13 得到 [1,3] 。
- 分割 25 得到 [2,5] 。
- 分割 83 得到 [8,3] 。
- 分割 77 得到 [7,7] 。
answer = [1,3,2,5,8,3,7,7] 。answer 中的数字分割结果按照原数字在数组中的相同顺序排列。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [7,1,3,9]
<b>输出：</b>[7,1,3,9]
<b>解释：</b>nums 中每个整数的分割是它自己。
answer = [7,1,3,9] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

将数组中的每个数字进行数位分割，然后将分割后的数字依次放入答案数组中。

时间复杂度 $O(n \times \log_{10} M)$，空间复杂度 $O(n \times \log_{10} M)$，其中 $n$ 为数组 `nums` 的长度，而 $M$ 为数组 `nums` 中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def separateDigits(self, nums: List[int]) -> List[int]:
        ans = []
        for x in nums:
            t = []
            while x:
                t.append(x % 10)
                x //= 10
            ans.extend(t[::-1])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int x : nums) {
            List<Integer> t = new ArrayList<>();
            for (; x > 0; x /= 10) {
                t.add(x % 10);
            }
            Collections.reverse(t);
            res.addAll(t);
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> separateDigits(vector<int>& nums) {
        vector<int> ans;
        for (int x : nums) {
            vector<int> t;
            for (; x; x /= 10) {
                t.push_back(x % 10);
            }
            while (t.size()) {
                ans.push_back(t.back());
                t.pop_back();
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func separateDigits(nums []int) (ans []int) {
	for _, x := range nums {
		t := []int{}
		for ; x > 0; x /= 10 {
			t = append(t, x%10)
		}
		for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
			t[i], t[j] = t[j], t[i]
		}
		ans = append(ans, t...)
	}
	return
}
```

### **TypeScript**

```ts
function separateDigits(nums: number[]): number[] {
    const ans: number[] = [];
    for (let num of nums) {
        const t: number[] = [];
        while (num) {
            t.push(num % 10);
            num = Math.floor(num / 10);
        }
        ans.push(...t.reverse());
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn separate_digits(nums: Vec<i32>) -> Vec<i32> {
        let mut ans = Vec::new();
        for &num in nums.iter() {
            let mut num = num;
            let mut t = Vec::new();
            while num != 0 {
                t.push(num % 10);
                num /= 10;
            }
            t.into_iter().rev().for_each(|v| ans.push(v));
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
int *separateDigits(int *nums, int numsSize, int *returnSize) {
    int n = 0;
    for (int i = 0; i < numsSize; i++) {
        int t = nums[i];
        while (t != 0) {
            t /= 10;
            n++;
        }
    }
    int *ans = malloc(sizeof(int) * n);
    for (int i = numsSize - 1, j = n - 1; i >= 0; i--) {
        int t = nums[i];
        while (t != 0) {
            ans[j--] = t % 10;
            t /= 10;
        }
    }
    *returnSize = n;
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
