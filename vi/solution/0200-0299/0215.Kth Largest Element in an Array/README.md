---
comments: true
difficulty: Medium
tags:
    - Array
    - Divide and Conquer
    - Quickselect
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array)

[中文文档](/solution/0200-0299/0215.Kth%20Largest%20Element%20in%20an%20Array/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code> và một số nguyên <code>k</code>, hãy trả về <em>phần tử lớn thứ</em> <code>k<sup>th</sup></code> <em>trong mảng</em>.</p>

<p>Lưu ý rằng đây là phần tử lớn thứ <code>k<sup>th</sup></code> theo thứ tự đã sắp xếp, không phải phần tử phân biệt thứ <code>k<sup>th</sup></code>.</p>

<p>Bạn có thể giải bài toán mà không cần sắp xếp không?</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [3,2,1,5,6,4], k = 2
<strong>Đầu ra:</strong> 5
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [3,2,3,1,2,4,5,5,6], k = 4
<strong>Đầu ra:</strong> 4
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quick Select

<!-- thinking:start -->

> **Tư duy**
>
> Sắp xếp toàn bộ sẽ tìm được phần tử lớn thứ $k$, nhưng đồng thời cũng sắp xếp mọi vị trí khác. Quickselect chỉ cần phần chứa thứ hạng cần tìm.
>
> Sau khi phân hoạch quanh một pivot, chúng ta chỉ đệ quy vào khoảng chứa thứ hạng đích. Cài đặt chuyển “phần tử lớn thứ $k$” thành “phần tử nhỏ thứ $(n-k)$”.

<!-- thinking:end -->

Quick Select là một thuật toán tìm phần tử lớn hoặc nhỏ thứ $k^{th}$ trong một mảng chưa sắp xếp. Ý tưởng cơ bản của thuật toán là chọn một phần tử pivot ở mỗi lần, chia mảng thành hai phần: một phần chứa các phần tử nhỏ hơn pivot, phần còn lại chứa các phần tử lớn hơn pivot. Sau đó, dựa trên vị trí của pivot, thuật toán quyết định tiếp tục tìm kiếm ở phía bên trái hay bên phải cho đến khi tìm được phần tử lớn thứ $k^{th}$.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(\log n)$. Trong đó, $n$ là độ dài của mảng $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        def quick_sort(l: int, r: int) -> int:
            if l == r:
                return nums[l]
            i, j = l - 1, r + 1
            x = nums[(l + r) >> 1]
            while i < j:
                while 1:
                    i += 1
                    if nums[i] >= x:
                        break
                while 1:
                    j -= 1
                    if nums[j] <= x:
                        break
                if i < j:
                    nums[i], nums[j] = nums[j], nums[i]
            if j < k:
                return quick_sort(j + 1, r)
            return quick_sort(l, j)

        n = len(nums)
        k = n - k
        return quick_sort(0, n - 1)
```

#### Java

```java
class Solution {
    private int[] nums;
    private int k;

    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        this.k = nums.length - k;
        return quickSort(0, nums.length - 1);
    }

    private int quickSort(int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int i = l - 1, j = r + 1;
        int x = nums[(l + r) >>> 1];
        while (i < j) {
            while (nums[++i] < x) {
            }
            while (nums[--j] > x) {
            }
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        if (j < k) {
            return quickSort(j + 1, r);
        }
        return quickSort(l, j);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        int n = nums.size();
        k = n - k;
        auto quickSort = [&](auto&& quickSort, int l, int r) -> int {
            if (l == r) {
                return nums[l];
            }
            int i = l - 1, j = r + 1;
            int x = nums[(l + r) >> 1];
            while (i < j) {
                while (nums[++i] < x) {
                }
                while (nums[--j] > x) {
                }
                if (i < j) {
                    swap(nums[i], nums[j]);
                }
            }
            if (j < k) {
                return quickSort(quickSort, j + 1, r);
            }
            return quickSort(quickSort, l, j);
        };
        return quickSort(quickSort, 0, n - 1);
    }
};
```

#### Go

```go
func findKthLargest(nums []int, k int) int {
	k = len(nums) - k
	var quickSort func(l, r int) int
	quickSort = func(l, r int) int {
		if l == r {
			return nums[l]
		}
		i, j := l-1, r+1
		x := nums[(l+r)>>1]
		for i < j {
			for {
				i++
				if nums[i] >= x {
					break
				}
			}
			for {
				j--
				if nums[j] <= x {
					break
				}
			}
			if i < j {
				nums[i], nums[j] = nums[j], nums[i]
			}
		}
		if j < k {
			return quickSort(j+1, r)
		}
		return quickSort(l, j)
	}
	return quickSort(0, len(nums)-1)
}
```

#### TypeScript

```ts
function findKthLargest(nums: number[], k: number): number {
    const n = nums.length;
    k = n - k;
    const quickSort = (l: number, r: number): number => {
        if (l === r) {
            return nums[l];
        }
        let [i, j] = [l - 1, r + 1];
        const x = nums[(l + r) >> 1];
        while (i < j) {
            while (nums[++i] < x);
            while (nums[--j] > x);
            if (i < j) {
                [nums[i], nums[j]] = [nums[j], nums[i]];
            }
        }
        if (j < k) {
            return quickSort(j + 1, r);
        }
        return quickSort(l, j);
    };
    return quickSort(0, n - 1);
}
```

#### Rust

```rust
impl Solution {
    pub fn find_kth_largest(mut nums: Vec<i32>, k: i32) -> i32 {
        let len = nums.len();
        let k = len - k as usize;
        Self::quick_sort(&mut nums, 0, len - 1, k)
    }

    fn quick_sort(nums: &mut Vec<i32>, l: usize, r: usize, k: usize) -> i32 {
        if l == r {
            return nums[l];
        }

        let (mut i, mut j) = (l as isize - 1, r as isize + 1);
        let x = nums[(l + r) / 2];

        while i < j {
            i += 1;
            while nums[i as usize] < x {
                i += 1;
            }

            j -= 1;
            while nums[j as usize] > x {
                j -= 1;
            }

            if i < j {
                nums.swap(i as usize, j as usize);
            }
        }

        let j = j as usize;
        if j < k {
            Self::quick_sort(nums, j + 1, r, k)
        } else {
            Self::quick_sort(nums, l, j, k)
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Hàng đợi ưu tiên (Min Heap)

<!-- thinking:start -->

> **Tư duy**
>
> Quickselect có độ phức tạp tuyến tính trung bình nhưng có thể đạt bậc hai trong trường hợp xấu nhất. Một min-heap có kích thước $k$ là đủ nếu chúng ta chỉ cần thứ hạng đó.
>
> Sau một lượt duyệt, heap lưu $k$ giá trị lớn nhất và phần tử trên cùng là đáp án, với thời gian $O(n\log k)$.

<!-- thinking:end -->

Chúng ta có thể duy trì một min heap $\textit{minQ}$ có kích thước $k$, sau đó duyệt qua mảng $\textit{nums}$ và thêm từng phần tử vào min heap. Khi kích thước của min heap vượt quá $k$, chúng ta lấy phần tử trên cùng của heap ra. Nhờ đó, $k$ phần tử cuối cùng trong min heap là $k$ phần tử lớn nhất trong mảng, và phần tử trên cùng của heap là phần tử lớn thứ $k^{th}$.

Độ phức tạp thời gian là $O(n\log k)$, và độ phức tạp không gian là $O(k)$. Trong đó, $n$ là độ dài của mảng $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        return nlargest(k, nums)[-1]
```

#### Java

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        for (int x : nums) {
            minQ.offer(x);
            if (minQ.size() > k) {
                minQ.poll();
            }
        }
        return minQ.peek();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        priority_queue<int, vector<int>, greater<int>> minQ;
        for (int x : nums) {
            minQ.push(x);
            if (minQ.size() > k) {
                minQ.pop();
            }
        }
        return minQ.top();
    }
};
```

#### Go

```go
func findKthLargest(nums []int, k int) int {
	minQ := hp{}
	for _, x := range nums {
		heap.Push(&minQ, x)
		if minQ.Len() > k {
			heap.Pop(&minQ)
		}
	}
	return minQ.IntSlice[0]
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

#### TypeScript

```ts
function findKthLargest(nums: number[], k: number): number {
    const minQ = new MinPriorityQueue<number>();
    for (const x of nums) {
        minQ.enqueue(x);
        if (minQ.size() > k) {
            minQ.dequeue();
        }
    }
    return minQ.front();
}
```

#### Rust

```rust
use std::collections::BinaryHeap;

impl Solution {
    pub fn find_kth_largest(nums: Vec<i32>, k: i32) -> i32 {
        let mut minQ = BinaryHeap::new();
        for &x in nums.iter() {
            minQ.push(-x);
            if minQ.len() > k as usize {
                minQ.pop();
            }
        }
        -minQ.peek().unwrap()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: Sắp xếp đếm

<!-- thinking:start -->

> **Tư duy**
>
> Heap vẫn phải trả thêm một thừa số log. Với miền giá trị bị giới hạn, chúng ta đếm tần suất, sau đó duyệt các giá trị từ lớn đến nhỏ cho đến khi đã thu thập đủ $k$ lần xuất hiện.
>
> Thời gian là tuyến tính theo $n$ cộng với miền giá trị, không cần sắp xếp theo phép so sánh.

<!-- thinking:end -->

Chúng ta có thể sử dụng ý tưởng của counting sort, đếm số lần xuất hiện của mỗi phần tử trong mảng $\textit{nums}$ và ghi lại vào một bảng băm $\textit{cnt}$. Sau đó, chúng ta duyệt qua các phần tử $i$ từ lớn nhất đến nhỏ nhất, mỗi lần trừ số lần xuất hiện $\textit{cnt}[i]$, cho đến khi $k$ nhỏ hơn hoặc bằng $0$. Khi đó, phần tử $i$ là phần tử lớn thứ $k^{th}$ trong mảng.

Độ phức tạp thời gian là $O(n + m)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng $\textit{nums}$, còn $m$ là giá trị lớn nhất trong các phần tử của $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        cnt = Counter(nums)
        for i in count(max(cnt), -1):
            k -= cnt[i]
            if k <= 0:
                return i
```

#### Java

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>(nums.length);
        int m = Integer.MIN_VALUE;
        for (int x : nums) {
            m = Math.max(m, x);
            cnt.merge(x, 1, Integer::sum);
        }
        for (int i = m;; --i) {
            k -= cnt.getOrDefault(i, 0);
            if (k <= 0) {
                return i;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        int m = INT_MIN;
        for (int x : nums) {
            ++cnt[x];
            m = max(m, x);
        }
        for (int i = m;; --i) {
            k -= cnt[i];
            if (k <= 0) {
                return i;
            }
        }
    }
};
```

#### Go

```go
func findKthLargest(nums []int, k int) int {
	cnt := map[int]int{}
	m := -(1 << 30)
	for _, x := range nums {
		cnt[x]++
		m = max(m, x)
	}
	for i := m; ; i-- {
		k -= cnt[i]
		if k <= 0 {
			return i
		}
	}

}
```

#### TypeScript

```ts
function findKthLargest(nums: number[], k: number): number {
    const cnt: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    const m = Math.max(...nums);
    for (let i = m; ; --i) {
        k -= cnt[i] || 0;
        if (k <= 0) {
            return i;
        }
    }
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn find_kth_largest(nums: Vec<i32>, k: i32) -> i32 {
        let mut cnt = HashMap::new();
        let mut m = i32::MIN;

        for &x in &nums {
            *cnt.entry(x).or_insert(0) += 1;
            if x > m {
                m = x;
            }
        }

        let mut k = k;
        for i in (i32::MIN..=m).rev() {
            if let Some(&count) = cnt.get(&i) {
                k -= count;
                if k <= 0 {
                    return i;
                }
            }
        }

        unreachable!();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
