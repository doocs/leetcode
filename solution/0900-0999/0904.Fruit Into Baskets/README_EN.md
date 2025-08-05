---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0904.Fruit%20Into%20Baskets/README_EN.md
tags:
    - Array
    - Hash Table
    - Sliding Window
---

<!-- problem:start -->

# [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets)

[中文文档](/solution/0900-0999/0904.Fruit%20Into%20Baskets/README.md)

## Description

<!-- description:start -->

<p>You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array <code>fruits</code> where <code>fruits[i]</code> is the <strong>type</strong> of fruit the <code>i<sup>th</sup></code> tree produces.</p>

<p>You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:</p>

<ul>
	<li>You only have <strong>two</strong> baskets, and each basket can only hold a <strong>single type</strong> of fruit. There is no limit on the amount of fruit each basket can hold.</li>
	<li>Starting from any tree of your choice, you must pick <strong>exactly one fruit</strong> from <strong>every</strong> tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.</li>
	<li>Once you reach a tree with fruit that cannot fit in your baskets, you must stop.</li>
</ul>

<p>Given the integer array <code>fruits</code>, return <em>the <strong>maximum</strong> number of fruits you can pick</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> fruits = [<u>1,2,1</u>]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can pick from all 3 trees.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> fruits = [0,<u>1,2,2</u>]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> fruits = [1,<u>2,3,2,2</u>]
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= fruits.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= fruits[i] &lt; fruits.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Sliding Window

We use a hash table $cnt$ to maintain the types and corresponding quantities of fruits in the current window, and use two pointers $j$ and $i$ to maintain the left and right boundaries of the window.

We traverse the $\textit{fruits}$ array, add the current fruit $x$ to the window, i.e., $cnt[x]++$, then judge whether the types of fruits in the current window exceed $2$. If it exceeds $2$, we need to move the left boundary $j$ of the window to the right until the types of fruits in the window do not exceed $2$. Then we update the answer, i.e., $ans = \max(ans, i - j + 1)$.

After the traversal ends, we can get the final answer.

```
1 2 3 2 2 1 4
^   ^
j   i


1 2 3 2 2 1 4
  ^ ^
  j i


1 2 3 2 2 1 4
  ^     ^
  j     i
```

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the $\textit{fruits}$ array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def totalFruit(self, fruits: List[int]) -> int:
        cnt = Counter()
        ans = j = 0
        for i, x in enumerate(fruits):
            cnt[x] += 1
            while len(cnt) > 2:
                y = fruits[j]
                cnt[y] -= 1
                if cnt[y] == 0:
                    cnt.pop(y)
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

#### Java

```java
class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; i < fruits.length; ++i) {
            int x = fruits[i];
            cnt.merge(x, 1, Integer::sum);
            while (cnt.size() > 2) {
                int y = fruits[j++];
                if (cnt.merge(y, -1, Integer::sum) == 0) {
                    cnt.remove(y);
                }
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int totalFruit(vector<int>& fruits) {
        unordered_map<int, int> cnt;
        int ans = 0;
        for (int i = 0, j = 0; i < fruits.size(); ++i) {
            int x = fruits[i];
            ++cnt[x];
            while (cnt.size() > 2) {
                int y = fruits[j++];
                if (--cnt[y] == 0) {
                    cnt.erase(y);
                }
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func totalFruit(fruits []int) int {
	cnt := map[int]int{}
	ans, j := 0, 0
	for i, x := range fruits {
		cnt[x]++
		for ; len(cnt) > 2; j++ {
			y := fruits[j]
			cnt[y]--
			if cnt[y] == 0 {
				delete(cnt, y)
			}
		}
		ans = max(ans, i-j+1)
	}
	return ans
}
```

#### TypeScript

```ts
function totalFruit(fruits: number[]): number {
    const n = fruits.length;
    const cnt: Map<number, number> = new Map();
    let ans = 0;
    for (let i = 0, j = 0; i < n; ++i) {
        cnt.set(fruits[i], (cnt.get(fruits[i]) || 0) + 1);
        for (; cnt.size > 2; ++j) {
            cnt.set(fruits[j], cnt.get(fruits[j])! - 1);
            if (!cnt.get(fruits[j])) {
                cnt.delete(fruits[j]);
            }
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn total_fruit(fruits: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        let mut ans = 0;
        let mut j = 0;

        for (i, &x) in fruits.iter().enumerate() {
            *cnt.entry(x).or_insert(0) += 1;

            while cnt.len() > 2 {
                let y = fruits[j];
                j += 1;
                *cnt.get_mut(&y).unwrap() -= 1;
                if cnt[&y] == 0 {
                    cnt.remove(&y);
                }
            }

            ans = ans.max(i - j + 1);
        }

        ans as i32
    }
}
```

#### C#

```cs
public class Solution {
    public int TotalFruit(int[] fruits) {
        var cnt = new Dictionary<int, int>();
        int ans = 0;
        for (int i = 0, j = 0; i < fruits.Length; ++i) {
            int x = fruits[i];
            if (cnt.ContainsKey(x)) {
                cnt[x]++;
            } else {
                cnt[x] = 1;
            }
            while (cnt.Count > 2) {
                int y = fruits[j++];
                if (cnt.ContainsKey(y)) {
                    cnt[y]--;
                    if (cnt[y] == 0) {
                        cnt.Remove(y);
                    }
                }
            }
            ans = Math.Max(ans, i - j + 1);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Monotonic Variable-Length Sliding Window

In Solution 1, we find that the window size sometimes increases and sometimes decreases, which requires us to update the answer each time.

But what this problem actually asks for is the maximum number of fruits, that is, the "largest" window. We don't need to shrink the window, we just need to let the window monotonically increase. So the code omits the operation of updating the answer each time, and only needs to return the size of the window as the answer after the traversal ends.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the $\textit{fruits}$ array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def totalFruit(self, fruits: List[int]) -> int:
        cnt = Counter()
        j = 0
        for x in fruits:
            cnt[x] += 1
            if len(cnt) > 2:
                y = fruits[j]
                cnt[y] -= 1
                if cnt[y] == 0:
                    cnt.pop(y)
                j += 1
        return len(fruits) - j
```

#### Java

```java
class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int j = 0, n = fruits.length;
        for (int x : fruits) {
            cnt.merge(x, 1, Integer::sum);
            if (cnt.size() > 2) {
                int y = fruits[j++];
                if (cnt.merge(y, -1, Integer::sum) == 0) {
                    cnt.remove(y);
                }
            }
        }
        return n - j;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int totalFruit(vector<int>& fruits) {
        unordered_map<int, int> cnt;
        int j = 0, n = fruits.size();
        for (int& x : fruits) {
            ++cnt[x];
            if (cnt.size() > 2) {
                int y = fruits[j++];
                if (--cnt[y] == 0) {
                    cnt.erase(y);
                }
            }
        }
        return n - j;
    }
};
```

#### Go

```go
func totalFruit(fruits []int) int {
	cnt := map[int]int{}
	j := 0
	for _, x := range fruits {
		cnt[x]++
		if len(cnt) > 2 {
			y := fruits[j]
			cnt[y]--
			if cnt[y] == 0 {
				delete(cnt, y)
			}
			j++
		}
	}
	return len(fruits) - j
}
```

#### TypeScript

```ts
function totalFruit(fruits: number[]): number {
    const n = fruits.length;
    const cnt: Map<number, number> = new Map();
    let j = 0;
    for (const x of fruits) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
        if (cnt.size > 2) {
            cnt.set(fruits[j], cnt.get(fruits[j])! - 1);
            if (!cnt.get(fruits[j])) {
                cnt.delete(fruits[j]);
            }
            ++j;
        }
    }
    return n - j;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn total_fruit(fruits: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        let mut j = 0;
        let n = fruits.len();

        for &x in &fruits {
            *cnt.entry(x).or_insert(0) += 1;
            if cnt.len() > 2 {
                let y = fruits[j];
                j += 1;
                *cnt.get_mut(&y).unwrap() -= 1;
                if cnt[&y] == 0 {
                    cnt.remove(&y);
                }
            }
        }

        (n - j) as i32
    }
}
```

#### C#

```cs
public class Solution {
    public int TotalFruit(int[] fruits) {
        var cnt = new Dictionary<int, int>();
        int j = 0, n = fruits.Length;
        foreach (int x in fruits) {
            if (cnt.ContainsKey(x)) {
                cnt[x]++;
            } else {
                cnt[x] = 1;
            }

            if (cnt.Count > 2) {
                int y = fruits[j++];
                if (cnt.ContainsKey(y)) {
                    cnt[y]--;
                    if (cnt[y] == 0) {
                        cnt.Remove(y);
                    }
                }
            }
        }
        return n - j;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
