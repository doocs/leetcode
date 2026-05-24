---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3941.Password%20Strength/README_EN.md
---

<!-- problem:start -->

# [3941. Password Strength](https://leetcode.com/problems/password-strength)

## Description

<!-- description:start -->

<p>You are given a string <code>password</code>.</p>

<p>The strength of the password is calculated based on the following rules:</p>

<ul>
<li>1 point for each distinct lowercase letter (<code>'a'</code> to <code>'z'</code>).</li>
<li>2 points for each distinct uppercase letter (<code>'A'</code> to <code>'Z'</code>).</li>
<li>3 points for each distinct digit (<code>'0'</code> to <code>'9'</code>).</li>
<li>5 points for each distinct special character from the set <code>"!@#$"</code>.</li>
</ul>

<p>Each character contributes at most once, even if it appears multiple times.</p>

<p>Return an integer denoting the strength of the password.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<div class="example-block">

<p><strong>Input:</strong> <span class="example-io">password = "aA1!"</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<p>The distinct characters are <code>'a'</code>, <code>'A'</code>, <code>'1'</code> and <code>'!'</code>.</p>

<p>Thus, the strength = 1 + 2 + 3 + 5 = 11.</p>

</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">

<p><strong>Input:</strong> <span class="example-io">password = "bbB11#"</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<p>The distinct characters are <code>'b'</code>, <code>'B'</code>, <code>'1'</code> and <code>'#'</code>.</p>

<p>Thus, the strength = 1 + 2 + 3 + 5 = 11.</p>

</div>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
<li><code>1 <= password.length <= 10<sup>5</sup></code></li>
<li><code>password</code> consists of lowercase and uppercase English letters, digits, and special characters from <code>"!@#$"</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: HashSet

We use a <code>HashSet</code> to store distinct characters.

For each distinct character:
- Add 1 if it is lowercase.
- Add 2 if it is uppercase.
- Add 3 if it is a digit.
- Add 5 if it is one of <code>!@#$</code>.

The time complexity is <code>O(n)</code>, where <code>n</code> is the length of the string.

The space complexity is <code>O(1)</code>.

<!-- tabs:start -->

#### Java

```java
class Solution {
    public int passwordStrength(String password) {
        Set<Character> set = new HashSet<>();

        for (char ch : password.toCharArray()) {
            set.add(ch);
        }

        int ans = 0;

        for (char ch : set) {
            if (Character.isLowerCase(ch)) {
                ans += 1;
            } else if (Character.isUpperCase(ch)) {
                ans += 2;
            } else if (Character.isDigit(ch)) {
                ans += 3;
            } else {
                ans += 5;
            }
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
