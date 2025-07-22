---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3324.Find%20the%20Sequence%20of%20Strings%20Appeared%20on%20the%20Screen/README_EN.md
rating: 1293
source: Weekly Contest 420 Q1
tags:
    - String
    - Simulation
---

<!-- problem:start -->

# [3324. Find the Sequence of Strings Appeared on the Screen](https://leetcode.com/problems/find-the-sequence-of-strings-appeared-on-the-screen)

[中文文档](/solution/3300-3399/3324.Find%20the%20Sequence%20of%20Strings%20Appeared%20on%20the%20Screen/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>target</code>.</p>

<p>Alice is going to type <code>target</code> on her computer using a special keyboard that has <strong>only two</strong> keys:</p>

<ul>
	<li>Key 1 appends the character <code>&quot;a&quot;</code> to the string on the screen.</li>
	<li>Key 2 changes the <strong>last</strong> character of the string on the screen to its <strong>next</strong> character in the English alphabet. For example, <code>&quot;c&quot;</code> changes to <code>&quot;d&quot;</code> and <code>&quot;z&quot;</code> changes to <code>&quot;a&quot;</code>.</li>
</ul>

<p><strong>Note</strong> that initially there is an <em>empty</em> string <code>&quot;&quot;</code> on the screen, so she can <strong>only</strong> press key 1.</p>

<p>Return a list of <em>all</em> strings that appear on the screen as Alice types <code>target</code>, in the order they appear, using the <strong>minimum</strong> key presses.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">target = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;a&quot;,&quot;aa&quot;,&quot;ab&quot;,&quot;aba&quot;,&quot;abb&quot;,&quot;abc&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p>The sequence of key presses done by Alice are:</p>

<ul>
	<li>Press key 1, and the string on the screen becomes <code>&quot;a&quot;</code>.</li>
	<li>Press key 1, and the string on the screen becomes <code>&quot;aa&quot;</code>.</li>
	<li>Press key 2, and the string on the screen becomes <code>&quot;ab&quot;</code>.</li>
	<li>Press key 1, and the string on the screen becomes <code>&quot;aba&quot;</code>.</li>
	<li>Press key 2, and the string on the screen becomes <code>&quot;abb&quot;</code>.</li>
	<li>Press key 2, and the string on the screen becomes <code>&quot;abc&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">target = &quot;he&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;,&quot;e&quot;,&quot;f&quot;,&quot;g&quot;,&quot;h&quot;,&quot;ha&quot;,&quot;hb&quot;,&quot;hc&quot;,&quot;hd&quot;,&quot;he&quot;]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 400</code></li>
	<li><code>target</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can simulate Alice's typing process, starting from an empty string and updating the string after each keystroke until the target string is obtained.

The time complexity is $O(n^2 \times |\Sigma|)$, where $n$ is the length of the target string and $\Sigma$ is the character set, which in this case is the set of lowercase letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stringSequence(self, target: str) -> List[str]:
        ans = []
        for c in target:
            s = ans[-1] if ans else ""
            for a in ascii_lowercase:
                t = s + a
                ans.append(t)
                if a == c:
                    break
        return ans
```

#### Java

```java
class Solution {
    public List<String> stringSequence(String target) {
        List<String> ans = new ArrayList<>();
        for (char c : target.toCharArray()) {
            String s = ans.isEmpty() ? "" : ans.get(ans.size() - 1);
            for (char a = 'a'; a <= c; ++a) {
                String t = s + a;
                ans.add(t);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> stringSequence(string target) {
        vector<string> ans;
        for (char c : target) {
            string s = ans.empty() ? "" : ans.back();
            for (char a = 'a'; a <= c; ++a) {
                string t = s + a;
                ans.push_back(t);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func stringSequence(target string) (ans []string) {
	for _, c := range target {
		s := ""
		if len(ans) > 0 {
			s = ans[len(ans)-1]
		}
		for a := 'a'; a <= c; a++ {
			t := s + string(a)
			ans = append(ans, t)
		}
	}
	return
}
```

#### TypeScript

```ts
function stringSequence(target: string): string[] {
    const ans: string[] = [];
    for (const c of target) {
        let s = ans.length > 0 ? ans[ans.length - 1] : '';
        for (let a = 'a'.charCodeAt(0); a <= c.charCodeAt(0); a++) {
            const t = s + String.fromCharCode(a);
            ans.push(t);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
