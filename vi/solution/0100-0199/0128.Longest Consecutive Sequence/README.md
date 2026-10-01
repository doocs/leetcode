---
comments: true
difficulty: Medium
tags:
    - Union Find
    - Array
    - Hash Table
---

<!-- problem:start -->

# [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence)

[中文文档](/solution/0100-0199/0128.Longest%20Consecutive%20Sequence/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên chưa được sắp xếp <code>nums</code>, hãy trả về <em>độ dài của dãy các phần tử liên tiếp dài nhất</em>.</p>

<p>Bạn phải viết một thuật toán chạy trong thời gian <code>O(n)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [100,4,200,1,3,2]
<strong>Đầu ra:</strong> 4
<strong>Giải thích:</strong> Dãy các phần tử liên tiếp dài nhất là <code>[1, 2, 3, 4]</code>. Do đó, độ dài của dãy là 4.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [0,3,7,2,5,8,4,6,0,1]
<strong>Đầu ra:</strong> 9
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,0,1,2]
<strong>Đầu ra:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm

<!-- thinking:start -->

> **Tư duy**
>
> Sắp xếp rồi duyệt các đoạn liên tiếp có độ phức tạp $O(n\log n)$, trong khi đề bài yêu cầu $O(n)$. $n \le 10^5$. Đưa các giá trị vào một hash set. Với mỗi $x$ còn trong tập, duyệt sang phải cho đến khi dãy bị ngắt, lưu độ dài đó ở đầu dãy, và để các đầu dãy trước đó nối tiếp vào. Mỗi số được đưa vào và rời khỏi tập đúng một lần.

<!-- thinking:end -->

Chúng ta có thể sử dụng một bảng băm $\textit{s}$ để lưu tất cả các phần tử trong mảng, một biến $\textit{ans}$ để ghi nhận độ dài của dãy liên tiếp dài nhất, và một bảng băm $\textit{d}$ để ghi nhận độ dài của dãy liên tiếp mà mỗi phần tử $x$ thuộc về.

Tiếp theo, chúng ta duyệt qua từng phần tử $x$ trong mảng, sử dụng một biến tạm $y$ để ghi nhận giá trị lớn nhất của dãy liên tiếp hiện tại, ban đầu $y = x$. Sau đó, chúng ta liên tục thử khớp $y+1, y+2, y+3, \dots$ cho đến khi không thể khớp nữa. Trong quá trình này, chúng ta xóa các phần tử đã khớp khỏi bảng băm $\textit{s}$. Độ dài của dãy liên tiếp mà phần tử hiện tại $x$ thuộc về là $d[x] = d[y] + y - x$, sau đó chúng ta cập nhật đáp án $\textit{ans} = \max(\textit{ans}, d[x])$.

Sau khi duyệt xong, chúng ta trả về đáp án $\textit{ans}$.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        s = set(nums)
        ans = 0
        d = defaultdict(int)
        for x in nums:
            y = x
            while y in s:
                s.remove(y)
                y += 1
            d[x] = d[y] + y - x
            ans = max(ans, d[x])
        return ans
```

#### Java

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        int ans = 0;
        Map<Integer, Integer> d = new HashMap<>();
        for (int x : nums) {
            int y = x;
            while (s.contains(y)) {
                s.remove(y++);
            }
            d.put(x, d.getOrDefault(y, 0) + y - x);
            ans = Math.max(ans, d.get(x));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = 0;
        unordered_map<int, int> d;
        for (int x : nums) {
            int y = x;
            while (s.contains(y)) {
                s.erase(y++);
            }
            d[x] = (d.contains(y) ? d[y] : 0) + y - x;
            ans = max(ans, d[x]);
        }
        return ans;
    }
};
```

#### Go

```go
func longestConsecutive(nums []int) (ans int) {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	d := map[int]int{}
	for _, x := range nums {
		y := x
		for s[y] {
			delete(s, y)
			y++
		}
		d[x] = d[y] + y - x
		ans = max(ans, d[x])
	}
	return
}
```

#### TypeScript

```ts
function longestConsecutive(nums: number[]): number {
    const s = new Set(nums);
    let ans = 0;
    const d = new Map<number, number>();
    for (const x of nums) {
        let y = x;
        while (s.has(y)) {
            s.delete(y++);
        }
        d.set(x, (d.get(y) || 0) + (y - x));
        ans = Math.max(ans, d.get(x)!);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::{HashMap, HashSet};

impl Solution {
    pub fn longest_consecutive(nums: Vec<i32>) -> i32 {
        let mut s: HashSet<i32> = nums.iter().cloned().collect();
        let mut ans = 0;
        let mut d: HashMap<i32, i32> = HashMap::new();
        for &x in &nums {
            let mut y = x;
            while s.contains(&y) {
                s.remove(&y);
                y += 1;
            }
            let length = d.get(&(y)).unwrap_or(&0) + y - x;
            d.insert(x, length);
            ans = ans.max(length);
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function (nums) {
    const s = new Set(nums);
    let ans = 0;
    const d = new Map();
    for (const x of nums) {
        let y = x;
        while (s.has(y)) {
            s.delete(y++);
        }
        d.set(x, (d.get(y) || 0) + (y - x));
        ans = Math.max(ans, d.get(x));
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Bảng băm (Tối ưu hóa)

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 lưu độ dài của mỗi dãy để các đầu dãy sau đó có thể nối tiếp vào. Nếu chỉ mở rộng dãy khi $x-1$ vắng mặt, thì $x$ đã là một đầu dãy, nên không cần bảng băm bổ sung $d$. Code ngắn hơn và vẫn chạm vào mỗi số một số lần hằng số.

<!-- thinking:end -->

Tương tự Lời giải 1, chúng ta sử dụng một bảng băm $\textit{s}$ để lưu tất cả các phần tử trong mảng và một biến $\textit{ans}$ để ghi nhận độ dài của dãy liên tiếp dài nhất. Tuy nhiên, chúng ta không còn sử dụng bảng băm $\textit{d}$ để ghi nhận độ dài của dãy liên tiếp mà mỗi phần tử $x$ thuộc về. Trong quá trình duyệt, chúng ta bỏ qua các phần tử khi $x-1$ cũng nằm trong bảng băm $\textit{s}$. Nếu $x-1$ nằm trong bảng băm $\textit{s}$, thì $x$ chắc chắn không phải là đầu của một dãy liên tiếp, nên chúng ta có thể bỏ qua $x$.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        s = set(nums)
        ans = 0
        for x in s:
            if x - 1 not in s:
                y = x + 1
                while y in s:
                    y += 1
                ans = max(ans, y - x)
        return ans
```

#### Java

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        int ans = 0;
        for (int x : s) {
            if (!s.contains(x - 1)) {
                int y = x + 1;
                while (s.contains(y)) {
                    ++y;
                }
                ans = Math.max(ans, y - x);
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
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = 0;
        for (int x : s) {
            if (!s.contains(x - 1)) {
                int y = x + 1;
                while (s.contains(y)) {
                    y++;
                }
                ans = max(ans, y - x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestConsecutive(nums []int) (ans int) {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for x, _ := range s {
		if !s[x-1] {
			y := x + 1
			for s[y] {
				y++
			}
			ans = max(ans, y-x)
		}
	}
	return
}
```

#### TypeScript

```ts
function longestConsecutive(nums: number[]): number {
    const s = new Set<number>(nums);
    let ans = 0;
    for (const x of s) {
        if (!s.has(x - 1)) {
            let y = x + 1;
            while (s.has(y)) {
                y++;
            }
            ans = Math.max(ans, y - x);
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn longest_consecutive(nums: Vec<i32>) -> i32 {
        let s: HashSet<i32> = nums.iter().cloned().collect();
        let mut ans = 0;
        for &x in &s {
            if !s.contains(&(x - 1)) {
                let mut y = x + 1;
                while s.contains(&y) {
                    y += 1;
                }
                ans = ans.max(y - x);
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function (nums) {
    const s = new Set(nums);
    let ans = 0;
    for (const x of nums) {
        if (!s.has(x - 1)) {
            let y = x + 1;
            while (s.has(y)) {
                y++;
            }
            ans = Math.max(ans, y - x);
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
