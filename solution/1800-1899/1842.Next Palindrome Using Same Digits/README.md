---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1842.Next%20Palindrome%20Using%20Same%20Digits/README.md
tags:
    - 双指针
    - 字符串
---

# [1842. 下个由相同数字构成的回文串 🔒](https://leetcode.cn/problems/next-palindrome-using-same-digits)

[English Version](/solution/1800-1899/1842.Next%20Palindrome%20Using%20Same%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个很长的数字回文串 <code>num</code> ，返回 <strong>大于</strong> <code>num</code>、<strong>由相同数字重新组合而成的最小</strong> 回文串。</p>

<p>如果不存在这样的回文串，则返回空串 <code>""</code>。</p>

<p><strong>回文串</strong> 是正读和反读都一样的字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>num = "1221"
<b>输出：</b>"2112"
<b>解释：</b>下个比<b> </b>"1221" 大的回文串是 "2112"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>num = "32123"
<b>输出：</b>""
<b>解释：</b>不存在通过重组 "32123" 的数字可得、比 "32123" 还大的回文串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>num = "45544554"
<b>输出：</b>"54455445"
<b>解释：</b>下个比 "45544554" 还要大的回文串是 "54455445"。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= num.length <= 10<sup>5</sup></code></li>
	<li><code>num</code> 是回文串。</li>
</ul>

## 解法

### 方法一：求前一半的下一个排列

根据题目描述，我们只需要求出前一半的下一个排列，然后遍历前一半，对称赋值后半部分即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

```python
class Solution:
    def nextPalindrome(self, num: str) -> str:
        def next_permutation(nums: List[str]) -> bool:
            n = len(nums) // 2
            i = n - 2
            while i >= 0 and nums[i] >= nums[i + 1]:
                i -= 1
            if i < 0:
                return False
            j = n - 1
            while j >= 0 and nums[j] <= nums[i]:
                j -= 1
            nums[i], nums[j] = nums[j], nums[i]
            nums[i + 1 : n] = nums[i + 1 : n][::-1]
            return True

        nums = list(num)
        if not next_permutation(nums):
            return ""
        n = len(nums)
        for i in range(n // 2):
            nums[n - i - 1] = nums[i]
        return "".join(nums)
```

```java
class Solution {
    public String nextPalindrome(String num) {
        char[] nums = num.toCharArray();
        if (!nextPermutation(nums)) {
            return "";
        }
        int n = nums.length;
        for (int i = 0; i < n / 2; ++i) {
            nums[n - 1 - i] = nums[i];
        }
        return String.valueOf(nums);
    }

    private boolean nextPermutation(char[] nums) {
        int n = nums.length / 2;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }
        if (i < 0) {
            return false;
        }
        int j = n - 1;
        while (j >= 0 && nums[i] >= nums[j]) {
            --j;
        }
        swap(nums, i++, j);
        for (j = n - 1; i < j; ++i, --j) {
            swap(nums, i, j);
        }
        return true;
    }

    private void swap(char[] nums, int i, int j) {
        char t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

```cpp
class Solution {
public:
    string nextPalindrome(string num) {
        int n = num.size();
        string nums = num.substr(0, n / 2);
        if (!next_permutation(begin(nums), end(nums))) {
            return "";
        }
        for (int i = 0; i < n / 2; ++i) {
            num[i] = nums[i];
            num[n - i - 1] = nums[i];
        }
        return num;
    }
};
```

```go
func nextPalindrome(num string) string {
	nums := []byte(num)
	n := len(nums)
	if !nextPermutation(nums) {
		return ""
	}
	for i := 0; i < n/2; i++ {
		nums[n-1-i] = nums[i]
	}
	return string(nums)
}

func nextPermutation(nums []byte) bool {
	n := len(nums) / 2
	i := n - 2
	for i >= 0 && nums[i] >= nums[i+1] {
		i--
	}
	if i < 0 {
		return false
	}
	j := n - 1
	for j >= 0 && nums[j] <= nums[i] {
		j--
	}
	nums[i], nums[j] = nums[j], nums[i]
	for i, j = i+1, n-1; i < j; i, j = i+1, j-1 {
		nums[i], nums[j] = nums[j], nums[i]
	}
	return true
}
```

```ts
function nextPalindrome(num: string): string {
    const nums = num.split('');
    const n = nums.length;
    if (!nextPermutation(nums)) {
        return '';
    }
    for (let i = 0; i < n >> 1; ++i) {
        nums[n - 1 - i] = nums[i];
    }
    return nums.join('');
}

function nextPermutation(nums: string[]): boolean {
    const n = nums.length >> 1;
    let i = n - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        i--;
    }
    if (i < 0) {
        return false;
    }
    let j = n - 1;
    while (j >= 0 && nums[i] >= nums[j]) {
        j--;
    }
    [nums[i], nums[j]] = [nums[j], nums[i]];
    for (i = i + 1, j = n - 1; i < j; ++i, --j) {
        [nums[i], nums[j]] = [nums[j], nums[i]];
    }
    return true;
}
```

<!-- tabs:end -->

<!-- end -->
