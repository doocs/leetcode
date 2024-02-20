# [3006. Find Beautiful Indices in the Given Array I](https://leetcode.com/problems/find-beautiful-indices-in-the-given-array-i)

[中文文档](/solution/3000-3099/3006.Find%20Beautiful%20Indices%20in%20the%20Given%20Array%20I/README.md)

<!-- tags:Two Pointers,String,Binary Search,String Matching,Hash Function,Rolling Hash -->

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code>, a string <code>a</code>, a string <code>b</code>, and an integer <code>k</code>.</p>

<p>An index <code>i</code> is <strong>beautiful</strong> if:</p>

<ul>
	<li><code>0 &lt;= i &lt;= s.length - a.length</code></li>
	<li><code>s[i..(i + a.length - 1)] == a</code></li>
	<li>There exists an index <code>j</code> such that:
	<ul>
		<li><code>0 &lt;= j &lt;= s.length - b.length</code></li>
		<li><code>s[j..(j + b.length - 1)] == b</code></li>
		<li><code>|j - i| &lt;= k</code></li>
	</ul>
	</li>
</ul>

<p>Return <em>the array that contains beautiful indices in <strong>sorted order from smallest to largest</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;isawsquirrelnearmysquirrelhouseohmy&quot;, a = &quot;my&quot;, b = &quot;squirrel&quot;, k = 15
<strong>Output:</strong> [16,33]
<strong>Explanation:</strong> There are 2 beautiful indices: [16,33].
- The index 16 is beautiful as s[16..17] == &quot;my&quot; and there exists an index 4 with s[4..11] == &quot;squirrel&quot; and |16 - 4| &lt;= 15.
- The index 33 is beautiful as s[33..34] == &quot;my&quot; and there exists an index 18 with s[18..25] == &quot;squirrel&quot; and |33 - 18| &lt;= 15.
Thus we return [16,33] as the result.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, a = &quot;a&quot;, b = &quot;a&quot;, k = 4
<strong>Output:</strong> [0]
<strong>Explanation:</strong> There is 1 beautiful index: [0].
- The index 0 is beautiful as s[0..0] == &quot;a&quot; and there exists an index 0 with s[0..0] == &quot;a&quot; and |0 - 0| &lt;= 4.
Thus we return [0] as the result.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= a.length, b.length &lt;= 10</code></li>
	<li><code>s</code>, <code>a</code>, and <code>b</code> contain only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def beautifulIndices(self, s: str, a: str, b: str, k: int) -> List[int]:
        def build_prefix_function(pattern):
            prefix_function = [0] * len(pattern)
            j = 0
            for i in range(1, len(pattern)):
                while j > 0 and pattern[i] != pattern[j]:
                    j = prefix_function[j - 1]
                if pattern[i] == pattern[j]:
                    j += 1
                prefix_function[i] = j
            return prefix_function

        def kmp_search(pattern, text, prefix_function):
            occurrences = []
            j = 0
            for i in range(len(text)):
                while j > 0 and text[i] != pattern[j]:
                    j = prefix_function[j - 1]
                if text[i] == pattern[j]:
                    j += 1
                if j == len(pattern):
                    occurrences.append(i - j + 1)
                    j = prefix_function[j - 1]
            return occurrences

        prefix_a = build_prefix_function(a)
        prefix_b = build_prefix_function(b)

        resa = kmp_search(a, s, prefix_a)
        resb = kmp_search(b, s, prefix_b)

        res = []
        print(resa, resb)
        i = 0
        j = 0
        while i < len(resa):
            while j < len(resb):
                if abs(resb[j] - resa[i]) <= k:
                    res.append(resa[i])
                    break
                elif j + 1 < len(resb) and abs(resb[j + 1] - resa[i]) < abs(
                    resb[j] - resa[i]
                ):
                    j += 1
                else:
                    break
            i += 1
        return res
```

```java
public class Solution {
    public void computeLPS(String pattern, int[] lps) {
        int M = pattern.length();
        int len = 0;

        lps[0] = 0;

        int i = 1;
        while (i < M) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    public List<Integer> KMP_codestorywithMIK(String pat, String txt) {
        int N = txt.length();
        int M = pat.length();
        List<Integer> result = new ArrayList<>();

        int[] lps = new int[M];
        computeLPS(pat, lps);

        int i = 0; // Index for text
        int j = 0; // Index for pattern

        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
            }

            if (j == M) {
                result.add(i - j); // Pattern found at index i-j+1 (If you have to return 1 Based
                                   // indexing, that's why added + 1)
                j = lps[j - 1];
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return result;
    }

    private int lowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1, result = list.size();

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid) >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        int n = s.length();

        List<Integer> i_indices = KMP_codestorywithMIK(a, s);
        List<Integer> j_indices = KMP_codestorywithMIK(b, s);

        List<Integer> result = new ArrayList<>();

        for (int i : i_indices) {

            int left_limit = Math.max(0, i - k); // To avoid out of bound -> I used max(0, i-k)
            int right_limit
                = Math.min(n - 1, i + k); // To avoid out of bound -> I used min(n-1, i+k)

            int lowerBoundIndex = lowerBound(j_indices, left_limit);

            if (lowerBoundIndex < j_indices.size()
                && j_indices.get(lowerBoundIndex) <= right_limit) {
                result.add(i);
            }
        }

        return result;
    }
}
```

```cpp
class Solution {
public:
    vector<int> beautifulIndices(string s, string patternA, string patternB, int k) {
        vector<int> beautifulIndicesA = kmpSearch(s, patternA);
        vector<int> beautifulIndicesB = kmpSearch(s, patternB);

        sort(beautifulIndicesB.begin(), beautifulIndicesB.end());

        vector<int> result;
        for (int indexA : beautifulIndicesA) {
            int left = lower_bound(beautifulIndicesB.begin(), beautifulIndicesB.end(), indexA - k) - beautifulIndicesB.begin();
            int right = lower_bound(beautifulIndicesB.begin(), beautifulIndicesB.end(), indexA + k + patternB.length()) - beautifulIndicesB.begin();

            left = (left >= 0) ? left : -(left + 1);
            right = (right >= 0) ? right : -(right + 1);

            for (int indexB = left; indexB < right; indexB++) {
                if (abs(beautifulIndicesB[indexB] - indexA) <= k) {
                    result.push_back(indexA);
                    break;
                }
            }
        }

        return result;
    }

private:
    vector<int> kmpSearch(string text, string pattern) {
        vector<int> indices;
        vector<int> pi = computePrefixFunction(pattern);

        int q = 0;
        for (int i = 0; i < text.length(); i++) {
            while (q > 0 && pattern[q] != text[i]) {
                q = pi[q - 1];
            }
            if (pattern[q] == text[i]) {
                q++;
            }
            if (q == pattern.length()) {
                indices.push_back(i - q + 1);
                q = pi[q - 1];
            }
        }

        return indices;
    }

    vector<int> computePrefixFunction(string pattern) {
        int m = pattern.length();
        vector<int> pi(m, 0);
        int k = 0;
        for (int q = 1; q < m; q++) {
            while (k > 0 && pattern[k] != pattern[q]) {
                k = pi[k - 1];
            }
            if (pattern[k] == pattern[q]) {
                k++;
            }
            pi[q] = k;
        }
        return pi;
    }
};
```

```go
func beautifulIndices(s string, a string, b string, k int) []int {

	s_len := len(s)
	a_len := len(a)
	b_len := len(b)

	final := make([]int, 0)
	lps_a := make([]int, a_len)
	lps_b := make([]int, b_len)
	a_index := make([]int, 0)
	b_index := make([]int, 0)

	var pat func(lps []int, s_l int, pattern string)

	pat = func(lps []int, s_l int, pattern string) {

		l := 0
		lps[0] = 0
		i := 1

		for i < s_l {
			if pattern[i] == pattern[l] {
				l++
				lps[i] = l
				i++
			} else {
				if l != 0 {
					l = lps[l-1]
				} else {
					lps[i] = l
					i++
				}
			}
		}
	}

	pat(lps_a, a_len, a)
	pat(lps_b, b_len, b)

	var kmp func(pat string, pat_l int, lps []int, index *[]int)

	kmp = func(pat string, pat_l int, lps []int, index *[]int) {
		i := 0
		j := 0
		for s_len-i >= pat_l-j {
			if s[i] == pat[j] {
				i++
				j++
			}
			if j == pat_l {
				*index = append(*index, i-pat_l)
				j = lps[j-1]
			} else if s[i] != pat[j] {
				if j != 0 {
					j = lps[j-1]
				} else {
					i++
				}
			}
		}
	}

	kmp(a, a_len, lps_a, &a_index)
	kmp(b, b_len, lps_b, &b_index)

	// fmt.Println(a_index, b_index)

	i := 0
	j := 0

	for i < len(a_index) && j < len(b_index) {
		if a_index[i]+k >= b_index[j] && a_index[i]-k <= b_index[j] {
			final = append(final, a_index[i])
			i++
		} else if a_index[i]-k > b_index[j] {
			j++
		} else {
			i++
		}
	}

	return final
}
```

<!-- tabs:end -->

<!-- end -->
