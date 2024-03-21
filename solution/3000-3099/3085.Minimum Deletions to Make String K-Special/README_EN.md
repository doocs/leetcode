# [3085. Minimum Deletions to Make String K-Special](https://leetcode.com/problems/minimum-deletions-to-make-string-k-special)

[中文文档](/solution/3000-3099/3085.Minimum%20Deletions%20to%20Make%20String%20K-Special/README.md)

<!-- tags:Greedy,Hash Table,String,Counting,Sorting -->

## Description

<p>You are given a string <code>word</code> and an integer <code>k</code>.</p>

<p>We consider <code>word</code> to be <strong>k-special</strong> if <code>|freq(word[i]) - freq(word[j])| &lt;= k</code> for all indices <code>i</code> and <code>j</code> in the string.</p>

<p>Here, <code>freq(x)</code> denotes the <span data-keyword="frequency-letter">frequency</span> of the character <code>x</code> in <code>word</code>, and <code>|y|</code> denotes the absolute value of <code>y</code>.</p>

<p>Return <em>the <strong>minimum</strong> number of characters you need to delete to make</em> <code>word</code> <strong><em>k-special</em></strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">word = &quot;aabcaba&quot;, k = 0</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">3</span></p>

<p><strong>Explanation:</strong> We can make <code>word</code> <code>0</code>-special by deleting <code>2</code> occurrences of <code>&quot;a&quot;</code> and <code>1</code> occurrence of <code>&quot;c&quot;</code>. Therefore, <code>word</code> becomes equal to <code>&quot;baba&quot;</code> where <code>freq(&#39;a&#39;) == freq(&#39;b&#39;) == 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">word = &quot;dabdcbdcdcd&quot;, k = 2</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">2</span></p>

<p><strong>Explanation:</strong> We can make <code>word</code> <code>2</code>-special by deleting <code>1</code> occurrence of <code>&quot;a&quot;</code> and <code>1</code> occurrence of <code>&quot;d&quot;</code>. Therefore, <code>word</code> becomes equal to &quot;bdcbdcdcd&quot; where <code>freq(&#39;b&#39;) == 2</code>, <code>freq(&#39;c&#39;) == 3</code>, and <code>freq(&#39;d&#39;) == 4</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">word = &quot;aaabaaa&quot;, k = 2</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">1</span></p>

<p><strong>Explanation:</strong> We can make <code>word</code> <code>2</code>-special by deleting <code>1</code> occurrence of <code>&quot;b&quot;</code>. Therefore, <code>word</code> becomes equal to <code>&quot;aaaaaa&quot;</code> where each letter&#39;s frequency is now uniformly <code>6</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Counting + Enumeration

First, we can count the occurrence of each character in the string and put all the counts into an array $nums$. Since the string only contains lowercase letters, the length of the array $nums$ will not exceed $26$.

Next, we can enumerate the minimum frequency $v$ of characters in the $K$ special strings within the range $[0,..n]$, and then use a function $f(v)$ to calculate the minimum number of deletions to adjust the frequency of all characters to $v$. The minimum value of all $f(v)$ is the answer.

The calculation method of function $f(v)$ is as follows:

Traverse each element $x$ in the array $nums$. If $x < v$, it means that we need to delete all characters with a frequency of $x$, and the number of deletions is $x$. If $x > v + k$, it means that we need to adjust all characters with a frequency of $x$ to $v + k$, and the number of deletions is $x - v - k$. The sum of all deletion counts is the value of $f(v)$.

The time complexity is $O(n \times |\Sigma|)$, and the space complexity is $O(|\Sigma|)$. Here, $n$ is the length of the string, and $|\Sigma|$ is the size of the character set. In this problem, $|\Sigma| = 26$.

<!-- tabs:start -->

```python
class Solution:
    def minimumDeletions(self, word: str, k: int) -> int:
        def f(v: int) -> int:
            ans = 0
            for x in nums:
                if x < v:
                    ans += x
                elif x > v + k:
                    ans += x - v - k
            return ans

        nums = Counter(word).values()
        return min(f(v) for v in range(len(word) + 1))
```

```java
class Solution {
    private List<Integer> nums = new ArrayList<>();

    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            ++freq[word.charAt(i) - 'a'];
        }
        for (int v : freq) {
            if (v > 0) {
                nums.add(v);
            }
        }
        int ans = n;
        for (int i = 0; i <= n; ++i) {
            ans = Math.min(ans, f(i, k));
        }
        return ans;
    }

    private int f(int v, int k) {
        int ans = 0;
        for (int x : nums) {
            if (x < v) {
                ans += x;
            } else if (x > v + k) {
                ans += x - v - k;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumDeletions(string word, int k) {
        int freq[26]{};
        for (char& c : word) {
            ++freq[c - 'a'];
        }
        vector<int> nums;
        for (int v : freq) {
            if (v) {
                nums.push_back(v);
            }
        }
        int n = word.size();
        int ans = n;
        auto f = [&](int v) {
            int ans = 0;
            for (int x : nums) {
                if (x < v) {
                    ans += x;
                } else if (x > v + k) {
                    ans += x - v - k;
                }
            }
            return ans;
        };
        for (int i = 0; i <= n; ++i) {
            ans = min(ans, f(i));
        }
        return ans;
    }
};
```

```go
func minimumDeletions(word string, k int) int {
	freq := [26]int{}
	for _, c := range word {
		freq[c-'a']++
	}
	nums := []int{}
	for _, v := range freq {
		if v > 0 {
			nums = append(nums, v)
		}
	}
	f := func(v int) int {
		ans := 0
		for _, x := range nums {
			if x < v {
				ans += x
			} else if x > v+k {
				ans += x - v - k
			}
		}
		return ans
	}
	ans := len(word)
	for i := 0; i <= len(word); i++ {
		ans = min(ans, f(i))
	}
	return ans
}
```

```ts
function minimumDeletions(word: string, k: number): number {
    const freq: number[] = Array(26).fill(0);
    for (const ch of word) {
        ++freq[ch.charCodeAt(0) - 97];
    }
    const nums = freq.filter(x => x > 0);
    const f = (v: number): number => {
        let ans = 0;
        for (const x of nums) {
            if (x < v) {
                ans += x;
            } else if (x > v + k) {
                ans += x - v - k;
            }
        }
        return ans;
    };
    return Math.min(...Array.from({ length: word.length + 1 }, (_, i) => f(i)));
}
```

<!-- tabs:end -->

<!-- end -->
