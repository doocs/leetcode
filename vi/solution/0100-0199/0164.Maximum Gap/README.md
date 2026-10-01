---
comments: true
difficulty: Medium
tags:
    - Array
    - Bucket Sort
    - Radix Sort
    - Sorting
    - Pigeonhole Principle
---

<!-- problem:start -->

# [164. Maximum Gap](https://leetcode.com/problems/maximum-gap)

[中文文档](/solution/0100-0199/0164.Maximum%20Gap/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code>, hãy trả về <em>độ chênh lệch lớn nhất giữa hai phần tử liên tiếp trong dạng đã sắp xếp</em>. Nếu mảng chứa ít hơn hai phần tử, hãy trả về <code>0</code>.</p>

<p>Bạn phải viết một thuật toán chạy trong thời gian tuyến tính và sử dụng không gian phụ tuyến tính.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [3,6,9,1]
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong> Dạng đã sắp xếp của mảng là [1,3,6,9], một trong hai cặp (3,6) hoặc (6,9) có độ chênh lệch lớn nhất là 3.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [10]
<strong>Đầu ra:</strong> 0
<strong>Giải thích:</strong> Mảng chứa ít hơn 2 phần tử, do đó trả về 0.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Thảo luận các trường hợp khác nhau

<!-- thinking:start -->

> **Tư duy**
>
> Khoảng cách lớn nhất sau khi sắp xếp, trong thời gian tuyến tính. Các thuật toán sắp xếp dựa trên phép so sánh có độ phức tạp $O(n\log n)$; $n\le 10^5$. Khoảng cách kề lớn nhất ít nhất là $(\textit{max}-\textit{min})/(n-1)$. Chia theo bucket có độ rộng đó: các khoảng cách bên trong một bucket nhỏ hơn cận dưới này, vì vậy đáp án nằm giữa các bucket không rỗng liên tiếp (min tiếp theo trừ max trước đó). Mỗi bucket chỉ lưu min và max.

<!-- thinking:end -->

Gọi $m$ là độ dài của chuỗi $s$, và $n$ là độ dài của chuỗi $t$. Ta có thể giả sử rằng $m$ luôn lớn hơn hoặc bằng $n$.

Nếu $m-n > 1$, trả về false ngay;

Nếu không, duyệt qua $s$ và $t$; nếu $s[i]$ không bằng $t[i]$:

- Nếu $m \neq n$, so sánh $s[i+1:]$ với $t[i:]$, trả về true nếu chúng bằng nhau, nếu không thì trả về false;
- Nếu $m = n$, so sánh $s[i:]$ với $t[i:]$, trả về true nếu chúng bằng nhau, nếu không thì trả về false.

Nếu quá trình lặp kết thúc, điều đó có nghĩa là tất cả các ký tự của $s$ và $t$ đã được duyệt đều bằng nhau; lúc này cần thỏa mãn $m=n+1$.

Độ phức tạp thời gian là $O(m)$, trong đó $m$ là độ dài của chuỗi $s$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumGap(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2:
            return 0
        mi, mx = min(nums), max(nums)
        bucket_size = max(1, (mx - mi) // (n - 1))
        bucket_count = (mx - mi) // bucket_size + 1
        buckets = [[inf, -inf] for _ in range(bucket_count)]
        for v in nums:
            i = (v - mi) // bucket_size
            buckets[i][0] = min(buckets[i][0], v)
            buckets[i][1] = max(buckets[i][1], v)
        ans = 0
        prev = inf
        for curmin, curmax in buckets:
            if curmin > curmax:
                continue
            ans = max(ans, curmin - prev)
            prev = curmax
        return ans
```

#### Java

```java
class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int inf = 0x3f3f3f3f;
        int mi = inf, mx = -inf;
        for (int v : nums) {
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
        }
        int bucketSize = Math.max(1, (mx - mi) / (n - 1));
        int bucketCount = (mx - mi) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][2];
        for (var bucket : buckets) {
            bucket[0] = inf;
            bucket[1] = -inf;
        }
        for (int v : nums) {
            int i = (v - mi) / bucketSize;
            buckets[i][0] = Math.min(buckets[i][0], v);
            buckets[i][1] = Math.max(buckets[i][1], v);
        }
        int prev = inf;
        int ans = 0;
        for (var bucket : buckets) {
            if (bucket[0] > bucket[1]) {
                continue;
            }
            ans = Math.max(ans, bucket[0] - prev);
            prev = bucket[1];
        }
        return ans;
    }
}
```

#### C++

```cpp
using pii = pair<int, int>;

class Solution {
public:
    const int inf = 0x3f3f3f3f;
    int maximumGap(vector<int>& nums) {
        int n = nums.size();
        if (n < 2) return 0;
        int mi = inf, mx = -inf;
        for (int v : nums) {
            mi = min(mi, v);
            mx = max(mx, v);
        }
        int bucketSize = max(1, (mx - mi) / (n - 1));
        int bucketCount = (mx - mi) / bucketSize + 1;
        vector<pii> buckets(bucketCount, {inf, -inf});
        for (int v : nums) {
            int i = (v - mi) / bucketSize;
            buckets[i].first = min(buckets[i].first, v);
            buckets[i].second = max(buckets[i].second, v);
        }
        int ans = 0;
        int prev = inf;
        for (auto [curmin, curmax] : buckets) {
            if (curmin > curmax) continue;
            ans = max(ans, curmin - prev);
            prev = curmax;
        }
        return ans;
    }
};
```

#### Go

```go
func maximumGap(nums []int) int {
	n := len(nums)
	if n < 2 {
		return 0
	}
	inf := 0x3f3f3f3f
	mi, mx := inf, -inf
	for _, v := range nums {
		mi = min(mi, v)
		mx = max(mx, v)
	}
	bucketSize := max(1, (mx-mi)/(n-1))
	bucketCount := (mx-mi)/bucketSize + 1
	buckets := make([][]int, bucketCount)
	for i := range buckets {
		buckets[i] = []int{inf, -inf}
	}
	for _, v := range nums {
		i := (v - mi) / bucketSize
		buckets[i][0] = min(buckets[i][0], v)
		buckets[i][1] = max(buckets[i][1], v)
	}
	ans := 0
	prev := inf
	for _, bucket := range buckets {
		if bucket[0] > bucket[1] {
			continue
		}
		ans = max(ans, bucket[0]-prev)
		prev = bucket[1]
	}
	return ans
}
```

#### C#

```cs
public class Solution {
    public int MaximumGap(int[] nums) {
        if (nums.Length < 2) return 0;
        var max = nums.Max();
        var min = nums.Min();
        var bucketSize = Math.Max(1, (max - min) / (nums.Length - 1));
        var buckets = new Tuple<int, int>[(max - min) / bucketSize + 1];
        foreach (var num in nums) {
            var index = (num - min) / bucketSize;
            if (buckets[index] == null) {
                buckets[index] = Tuple.Create(num, num);
            }
            else {
                buckets[index] = Tuple.Create(Math.Min(buckets[index].Item1, num), Math.Max(buckets[index].Item2, num));
            }
        }

        var result = 0;
        Tuple<int, int> lastBucket = null;
        for (var i = 0; i < buckets.Length; ++i) {
            if (buckets[i] != null) {
                if (lastBucket != null) {
                    result = Math.Max(result, buckets[i].Item1 - lastBucket.Item2);
                }
                lastBucket = buckets[i];
            }
        }
        return result;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
