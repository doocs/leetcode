---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0916.Word%20Subsets/README_EN.md
tags:
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [916. Word Subsets](https://leetcode.com/problems/word-subsets)

[中文文档](/solution/0900-0999/0916.Word%20Subsets/README.md)

## Description

<!-- description:start -->

<p>You are given two string arrays <code>words1</code> and <code>words2</code>.</p>

<p>A string <code>b</code> is a <strong>subset</strong> of string <code>a</code> if every letter in <code>b</code> occurs in <code>a</code> including multiplicity.</p>

<ul>
	<li>For example, <code>&quot;wrr&quot;</code> is a subset of <code>&quot;warrior&quot;</code> but is not a subset of <code>&quot;world&quot;</code>.</li>
</ul>

<p>A string <code>a</code> from <code>words1</code> is <strong>universal</strong> if for every string <code>b</code> in <code>words2</code>, <code>b</code> is a subset of <code>a</code>.</p>

<p>Return an array of all the <strong>universal</strong> strings in <code>words1</code>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words1 = [&quot;amazon&quot;,&quot;apple&quot;,&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;], words2 = [&quot;e&quot;,&quot;o&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;]</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words1 = [&quot;amazon&quot;,&quot;apple&quot;,&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;], words2 = [&quot;lc&quot;,&quot;eo&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;leetcode&quot;]</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words1 = [&quot;acaac&quot;,&quot;cccbb&quot;,&quot;aacbb&quot;,&quot;caacc&quot;,&quot;bcbbb&quot;], words2 = [&quot;c&quot;,&quot;cc&quot;,&quot;b&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;cccbb&quot;]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words1.length, words2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words1[i].length, words2[i].length &lt;= 10</code></li>
	<li><code>words1[i]</code> and <code>words2[i]</code> consist only of lowercase English letters.</li>
	<li>All the strings of <code>words1</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

Traverse each word `b` in `words2`, count the maximum occurrence of each letter, and record it as `cnt`.

Then traverse each word `a` in `words1`, count the occurrence of each letter, and record it as `t`. If the occurrence of each letter in `cnt` is not greater than the occurrence in `t`, then `a` is a universal word, and add it to the answer.

The time complexity is $O(L)$, where $L$ is the sum of the lengths of all words in `words1` and `words2`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def wordSubsets(self, words1: List[str], words2: List[str]) -> List[str]:
        cnt = Counter()
        for b in words2:
            t = Counter(b)
            for c, v in t.items():
                cnt[c] = max(cnt[c], v)
        ans = []
        for a in words1:
            t = Counter(a)
            if all(v <= t[c] for c, v in cnt.items()):
                ans.append(a)
        return ans
```

#### Java

```java
class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] cnt = new int[26];
        for (var b : words2) {
            int[] t = new int[26];
            for (int i = 0; i < b.length(); ++i) {
                t[b.charAt(i) - 'a']++;
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = Math.max(cnt[i], t[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (var a : words1) {
            int[] t = new int[26];
            for (int i = 0; i < a.length(); ++i) {
                t[a.charAt(i) - 'a']++;
            }
            boolean ok = true;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.add(a);
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
    vector<string> wordSubsets(vector<string>& words1, vector<string>& words2) {
        int cnt[26] = {0};
        int t[26];
        for (auto& b : words2) {
            memset(t, 0, sizeof t);
            for (auto& c : b) {
                t[c - 'a']++;
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = max(cnt[i], t[i]);
            }
        }
        vector<string> ans;
        for (auto& a : words1) {
            memset(t, 0, sizeof t);
            for (auto& c : a) {
                t[c - 'a']++;
            }
            bool ok = true;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.emplace_back(a);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func wordSubsets(words1 []string, words2 []string) (ans []string) {
	cnt := [26]int{}
	for _, b := range words2 {
		t := [26]int{}
		for _, c := range b {
			t[c-'a']++
		}
		for i := range cnt {
			cnt[i] = max(cnt[i], t[i])
		}
	}
	for _, a := range words1 {
		t := [26]int{}
		for _, c := range a {
			t[c-'a']++
		}
		ok := true
		for i, v := range cnt {
			if v > t[i] {
				ok = false
				break
			}
		}
		if ok {
			ans = append(ans, a)
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
