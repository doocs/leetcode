---
comments: true
difficulty: Medium
tags:
    - Greedy
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [45. Jump Game II](https://leetcode.com/problems/jump-game-ii)

[中文文档](/solution/0000-0099/0045.Jump%20Game%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cho một mảng số nguyên <code>nums</code> được đánh chỉ số từ <strong>0</strong> có độ dài <code>n</code>. Ban đầu, bạn đang ở chỉ số 0.</p>

<p>Mỗi phần tử <code>nums[i]</code> biểu thị độ dài tối đa của một bước nhảy về phía trước từ chỉ số <code>i</code>. Nói cách khác, nếu bạn đang ở chỉ số <code>i</code>, bạn có thể nhảy đến bất kỳ chỉ số <code>(i + j)</code> nào, trong đó:</p>

<ul>
	<li><code>0 &lt;= j &lt;= nums[i]</code> và</li>
	<li><code>i + j &lt; n</code></li>
</ul>

<p>Trả về <em>số lần nhảy ít nhất để đến chỉ số </em><code>n - 1</code>. Các trường hợp kiểm thử được tạo sao cho bạn có thể đến chỉ số&nbsp;<code>n - 1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [2,3,1,1,4]
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> Số lần nhảy ít nhất để đến chỉ số cuối cùng là 2. Nhảy 1 bước từ chỉ số 0 đến 1, sau đó nhảy 3 bước đến chỉ số cuối cùng.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [2,3,0,1,4]
<strong>Đầu ra:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
	<li>Đảm bảo rằng bạn có thể đến <code>nums[n - 1]</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Thuật toán tham lam

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là DP: $f[i]$ là số lần nhảy ít nhất để đến $i$, và từ $i$ ta cập nhật các vị trí $i+1..i+\textit{nums}[i]$. Điều này đúng, nhưng trong trường hợp xấu nhất là $O(n^2)$. Với $n \le 10^4$, cách này có thể vượt qua, nhưng ta chỉ cần đến đích, không cần giá trị tại mọi chỉ số.
>
> Nút thắt là phải duy trì số lần nhảy nhỏ nhất chính xác cho từng vị trí. Bước nhảy $k$ bao phủ một đoạn liên tiếp; vị trí xa nhất có thể đạt tới bên trong đoạn đó là đầu phải của bước nhảy $k+1$.
>
> Duyệt một lần, theo dõi ranh giới của bước nhảy hiện tại và vị trí xa nhất mà bước nhảy tiếp theo có thể đạt tới. Khi chạm ranh giới, ta buộc phải thực hiện một bước nhảy. Đây là BFS theo từng lớp mà không cần hàng đợi.

<!-- thinking:end -->

Ta có thể dùng biến $mx$ để ghi nhận vị trí xa nhất có thể đạt tới từ vị trí hiện tại, biến $last$ để ghi nhận vị trí của bước nhảy cuối cùng, và biến $ans$ để ghi nhận số lần nhảy.

Tiếp theo, ta duyệt từng vị trí $i$ trong $[0,..n - 2]$. Với mỗi vị trí $i$, ta có thể tính vị trí xa nhất có thể đạt tới từ vị trí hiện tại thông qua $i + nums[i]$. Ta dùng $mx$ để ghi nhận vị trí xa nhất này, tức là $mx = max(mx, i + nums[i])$. Sau đó, ta kiểm tra xem vị trí hiện tại đã chạm ranh giới của bước nhảy cuối cùng hay chưa, tức là $i = last$. Nếu đã chạm, ta cần thực hiện một bước nhảy, cập nhật $last$ thành $mx$, đồng thời tăng số lần nhảy $ans$ lên $1$.

Cuối cùng, ta trả về số lần nhảy $ans$.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng. Độ phức tạp không gian là $O(1)$.

Các bài toán tương tự:

- [55. Jump Game](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0055.Jump%20Game/README_EN.md)
- [1024. Video Stitching](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1024.Video%20Stitching/README_EN.md)
- [1326. Minimum Number of Taps to Open to Water a Garden](https://github.com/doocs/leetcode/blob/main/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def jump(self, nums: List[int]) -> int:
        ans = mx = last = 0
        for i, x in enumerate(nums[:-1]):
            mx = max(mx, i + x)
            if last == i:
                ans += 1
                last = mx
        return ans
```

#### Java

```java
class Solution {
    public int jump(int[] nums) {
        int ans = 0, mx = 0, last = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            mx = Math.max(mx, i + nums[i]);
            if (last == i) {
                ++ans;
                last = mx;
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
    int jump(vector<int>& nums) {
        int ans = 0, mx = 0, last = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            mx = max(mx, i + nums[i]);
            if (last == i) {
                ++ans;
                last = mx;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func jump(nums []int) (ans int) {
	mx, last := 0, 0
	for i, x := range nums[:len(nums)-1] {
		mx = max(mx, i+x)
		if last == i {
			ans++
			last = mx
		}
	}
	return
}
```

#### TypeScript

```ts
function jump(nums: number[]): number {
    let [ans, mx, last] = [0, 0, 0];
    for (let i = 0; i < nums.length - 1; ++i) {
        mx = Math.max(mx, i + nums[i]);
        if (last === i) {
            ++ans;
            last = mx;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn jump(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        let mut last = 0;
        for i in 0..(nums.len() - 1) {
            mx = mx.max(i as i32 + nums[i]);
            if last == i as i32 {
                ans += 1;
                last = mx;
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int Jump(int[] nums) {
        int ans = 0, mx = 0, last = 0;
        for (int i = 0; i < nums.Length - 1; ++i) {
            mx = Math.Max(mx, i + nums[i]);
            if (last == i) {
                ++ans;
                last = mx;
            }
        }
        return ans;
    }
}
```

#### C

```c
int jump(int* nums, int numsSize) {
    int ans = 0;
    int mx = 0;
    int last = 0;
    for (int i = 0; i < numsSize - 1; ++i) {
        mx = (mx > i + nums[i]) ? mx : (i + nums[i]);
        if (last == i) {
            ++ans;
            last = mx;
        }
    }
    return ans;
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function jump($nums) {
        $ans = 0;
        $mx = 0;
        $last = 0;

        for ($i = 0; $i < count($nums) - 1; $i++) {
            $mx = max($mx, $i + $nums[$i]);
            if ($last == $i) {
                $ans++;
                $last = $mx;
            }
        }

        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
