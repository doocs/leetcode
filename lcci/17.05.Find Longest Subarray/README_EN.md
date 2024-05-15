---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.05.Find%20Longest%20Subarray/README_EN.md
---

# [17.05. Find Longest Subarray](https://leetcode.cn/problems/find-longest-subarray-lcci)

[中文文档](/lcci/17.05.Find%20Longest%20Subarray/README.md)

## Description

<p>Given an array filled with letters and numbers, find the longest subarray with an equal number of letters and numbers.</p>

<p>Return the subarray. If there are more than one answer, return the one which has the smallest&nbsp;index of its left endpoint. If there is no answer, return an empty arrary.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>[&quot;A&quot;,&quot;1&quot;,&quot;B&quot;,&quot;C&quot;,&quot;D&quot;,&quot;2&quot;,&quot;3&quot;,&quot;4&quot;,&quot;E&quot;,&quot;5&quot;,&quot;F&quot;,&quot;G&quot;,&quot;6&quot;,&quot;7&quot;,&quot;H&quot;,&quot;I&quot;,&quot;J&quot;,&quot;K&quot;,&quot;L&quot;,&quot;M&quot;]



<strong>Output: </strong>[&quot;A&quot;,&quot;1&quot;,&quot;B&quot;,&quot;C&quot;,&quot;D&quot;,&quot;2&quot;,&quot;3&quot;,&quot;4&quot;,&quot;E&quot;,&quot;5&quot;,&quot;F&quot;,&quot;G&quot;,&quot;6&quot;,&quot;7&quot;]

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>[&quot;A&quot;,&quot;A&quot;]



<strong>Output: </strong>[]

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>array.length &lt;= 100000</code></li>
</ul>

## Solutions

### Solution 1: Prefix Sum + Hash Table

The problem requires finding the longest subarray with an equal number of characters and digits. We can treat characters as $1$ and digits as $-1$, transforming the problem into finding the longest subarray with a sum of $0$.

We can use the idea of prefix sums and a hash table `vis` to record the first occurrence of each prefix sum. We use variables `mx` and `k` to record the length and the left endpoint of the longest subarray that meets the conditions, respectively.

Next, we iterate through the array, calculating the prefix sum `s` at the current position `i`:

-   If the prefix sum `s` at the current position exists in the hash table `vis`, we denote the first occurrence of `s` as `j`, then the sum of the subarray in the interval $[j + 1,..,i]$ is $0$. If the length of the current subarray is greater than the length of the longest subarray found so far, i.e., $mx < i - j$, we update `mx = i - j` and `k = j + 1`.
-   Otherwise, we store the current prefix sum `s` as the key and the current position `i` as the value in the hash table `vis`.

After the iteration, we return the subarray with a left endpoint of `k` and a length of `mx`.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def findLongestSubarray(self, array: List[str]) -> List[str]:
        vis = {0: -1}
        s = mx = k = 0
        for i, x in enumerate(array):
            s += 1 if x.isalpha() else -1
            if s in vis:
                if mx < i - (j := vis[s]):
                    mx = i - j
                    k = j + 1
            else:
                vis[s] = i
        return array[k : k + mx]
```

```java
class Solution {
    public String[] findLongestSubarray(String[] array) {
        Map<Integer, Integer> vis = new HashMap<>();
        vis.put(0, -1);
        int s = 0, mx = 0, k = 0;
        for (int i = 0; i < array.length; ++i) {
            s += array[i].charAt(0) >= 'A' ? 1 : -1;
            if (vis.containsKey(s)) {
                int j = vis.get(s);
                if (mx < i - j) {
                    mx = i - j;
                    k = j + 1;
                }
            } else {
                vis.put(s, i);
            }
        }
        String[] ans = new String[mx];
        System.arraycopy(array, k, ans, 0, mx);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> findLongestSubarray(vector<string>& array) {
        unordered_map<int, int> vis{{0, -1}};
        int s = 0, mx = 0, k = 0;
        for (int i = 0; i < array.size(); ++i) {
            s += array[i][0] >= 'A' ? 1 : -1;
            if (vis.count(s)) {
                int j = vis[s];
                if (mx < i - j) {
                    mx = i - j;
                    k = j + 1;
                }
            } else {
                vis[s] = i;
            }
        }
        return vector<string>(array.begin() + k, array.begin() + k + mx);
    }
};
```

```go
func findLongestSubarray(array []string) []string {
	vis := map[int]int{0: -1}
	var s, mx, k int
	for i, x := range array {
		if x[0] >= 'A' {
			s++
		} else {
			s--
		}
		if j, ok := vis[s]; ok {
			if mx < i-j {
				mx = i - j
				k = j + 1
			}
		} else {
			vis[s] = i
		}
	}
	return array[k : k+mx]
}
```

```ts
function findLongestSubarray(array: string[]): string[] {
    const vis = new Map();
    vis.set(0, -1);
    let s = 0,
        mx = 0,
        k = 0;
    for (let i = 0; i < array.length; ++i) {
        s += array[i] >= 'A' ? 1 : -1;
        if (vis.has(s)) {
            const j = vis.get(s);
            if (mx < i - j) {
                mx = i - j;
                k = j + 1;
            }
        } else {
            vis.set(s, i);
        }
    }
    return array.slice(k, k + mx);
}
```

```swift
class Solution {
    func findLongestSubarray(_ array: [String]) -> [String] {
        var vis: [Int: Int] = [0: -1]
        var s = 0, mx = 0, k = 0

        for i in 0..<array.count {
            s += array[i].first!.isLetter ? 1 : -1
            if let j = vis[s] {
                if mx < i - j {
                    mx = i - j
                    k = j + 1
                }
            } else {
                vis[s] = i
            }
        }

        return Array(array[k..<(k + mx)])
    }
}
```

<!-- tabs:end -->

<!-- end -->
