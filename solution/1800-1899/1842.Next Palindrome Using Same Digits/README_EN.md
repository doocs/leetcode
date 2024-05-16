---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1842.Next%20Palindrome%20Using%20Same%20Digits/README_EN.md
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [1842. Next Palindrome Using Same Digits 🔒](https://leetcode.com/problems/next-palindrome-using-same-digits)

[中文文档](/solution/1800-1899/1842.Next%20Palindrome%20Using%20Same%20Digits/README.md)

## Description

<p>You are given a numeric string <code>num</code>, representing a very large <strong>palindrome</strong>.</p>

<p>Return<em> the <strong>smallest palindrome larger than </strong></em><code>num</code><em> that can be created by rearranging its digits. If no such palindrome exists, return an empty string </em><code>&quot;&quot;</code>.</p>

<p>A <strong>palindrome</strong> is a number that reads the same backward as forward.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;1221&quot;
<strong>Output:</strong> &quot;2112&quot;
<strong>Explanation:</strong>&nbsp;The next palindrome larger than &quot;1221&quot; is &quot;2112&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;32123&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong>&nbsp;No palindromes larger than &quot;32123&quot; can be made by rearranging the digits.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;45544554&quot;
<strong>Output:</strong> &quot;54455445&quot;
<strong>Explanation:</strong> The next palindrome larger than &quot;45544554&quot; is &quot;54455445&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num</code> is a <strong>palindrome</strong>.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Find the Next Permutation of the First Half

According to the problem description, we only need to find the next permutation of the first half of the string, then traverse the first half and symmetrically assign values to the second half.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the string.

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

<!-- solution:end -->

<!-- problem:end -->
