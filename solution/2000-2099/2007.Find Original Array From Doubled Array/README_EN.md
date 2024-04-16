# [2007. Find Original Array From Doubled Array](https://leetcode.com/problems/find-original-array-from-doubled-array)

[中文文档](/solution/2000-2099/2007.Find%20Original%20Array%20From%20Doubled%20Array/README.md)

<!-- tags:Greedy,Array,Hash Table,Sorting -->

## Description

<p>An integer array <code>original</code> is transformed into a <strong>doubled</strong> array <code>changed</code> by appending <strong>twice the value</strong> of every element in <code>original</code>, and then randomly <strong>shuffling</strong> the resulting array.</p>

<p>Given an array <code>changed</code>, return <code>original</code><em> if </em><code>changed</code><em> is a <strong>doubled</strong> array. If </em><code>changed</code><em> is not a <strong>doubled</strong> array, return an empty array. The elements in</em> <code>original</code> <em>may be returned in <strong>any</strong> order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> changed = [1,3,4,2,6,8]
<strong>Output:</strong> [1,3,4]
<strong>Explanation:</strong> One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> changed = [6,3,0,1]
<strong>Output:</strong> []
<strong>Explanation:</strong> changed is not a doubled array.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> changed = [1]
<strong>Output:</strong> []
<strong>Explanation:</strong> changed is not a doubled array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= changed.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= changed[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Sorting

We notice that if the array `changed` is a double array, then the smallest element in the array `changed` must also be an element in the original array. Therefore, we can first sort the array `changed`, and then start from the first element to traverse the array `changed` in ascending order.

We use a hash table or array $cnt$ to count the occurrence of each element in the array `changed`. For each element $x$ in the array `changed`, we first check whether $x$ exists in $cnt$. If it does not exist, we skip this element. Otherwise, we subtract one from $cnt[x]$, and check whether $x \times 2$ exists in $cnt$. If it does not exist, we return an empty array directly. Otherwise, we subtract one from $cnt[x \times 2]$, and add $x$ to the answer array.

After the traversal, we return the answer array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the length of the array `changed`.

<!-- tabs:start -->

```python
class Solution:
    def findOriginalArray(self, changed: List[int]) -> List[int]:
        changed.sort()
        cnt = Counter(changed)
        ans = []
        for x in changed:
            if cnt[x] == 0:
                continue
            cnt[x] -= 1
            if cnt[x << 1] <= 0:
                return []
            cnt[x << 1] -= 1
            ans.append(x)
        return ans
```

```java
class Solution {
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        Arrays.sort(changed);
        int[] cnt = new int[changed[n - 1] + 1];
        for (int x : changed) {
            ++cnt[x];
        }
        int[] ans = new int[n >> 1];
        int i = 0;
        for (int x : changed) {
            if (cnt[x] == 0) {
                continue;
            }
            --cnt[x];
            int y = x << 1;
            if (y >= cnt.length || cnt[y] <= 0) {
                return new int[0];
            }
            --cnt[y];
            ans[i++] = x;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findOriginalArray(vector<int>& changed) {
        sort(changed.begin(), changed.end());
        vector<int> cnt(changed.back() + 1);
        for (int x : changed) {
            ++cnt[x];
        }
        vector<int> ans;
        for (int x : changed) {
            if (cnt[x] == 0) {
                continue;
            }
            --cnt[x];
            int y = x << 1;
            if (y >= cnt.size() || cnt[y] <= 0) {
                return {};
            }
            --cnt[y];
            ans.push_back(x);
        }
        return ans;
    }
};
```

```go
func findOriginalArray(changed []int) (ans []int) {
	sort.Ints(changed)
	cnt := make([]int, changed[len(changed)-1]+1)
	for _, x := range changed {
		cnt[x]++
	}
	for _, x := range changed {
		if cnt[x] == 0 {
			continue
		}
		cnt[x]--
		y := x << 1
		if y >= len(cnt) || cnt[y] <= 0 {
			return []int{}
		}
		cnt[y]--
		ans = append(ans, x)
	}
	return
}
```

```ts
function findOriginalArray(changed: number[]): number[] {
    changed.sort((a, b) => a - b);
    const cnt: number[] = Array(changed.at(-1)! + 1).fill(0);
    for (const x of changed) {
        ++cnt[x];
    }
    const ans: number[] = [];
    for (const x of changed) {
        if (cnt[x] === 0) {
            continue;
        }
        cnt[x]--;
        const y = x << 1;
        if (y >= cnt.length || cnt[y] <= 0) {
            return [];
        }
        cnt[y]--;
        ans.push(x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
