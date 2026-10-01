---
comments: true
difficulty: Medium
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [134. Gas Station](https://leetcode.com/problems/gas-station)

[中文文档](/solution/0100-0199/0134.Gas%20Station/README.md)

## Mô tả

<!-- description:start -->

<p>Có <code>n</code> trạm xăng dọc theo một tuyến đường vòng, trong đó lượng xăng tại trạm thứ <code>i<sup>th</sup></code> là <code>gas[i]</code>.</p>

<p>Bạn có một chiếc xe với bình xăng không giới hạn và cần <code>cost[i]</code> đơn vị xăng để đi từ trạm thứ <code>i<sup>th</sup></code> đến trạm kế tiếp là trạm thứ <code>(i + 1)<sup>th</sup></code>. Bạn bắt đầu hành trình với bình xăng rỗng tại một trong các trạm xăng.</p>

<p>Cho hai mảng số nguyên <code>gas</code> và <code>cost</code>, hãy trả về <em>chỉ số của trạm xăng bắt đầu nếu bạn có thể đi quanh mạch một lần theo chiều kim đồng hồ, nếu không hãy trả về</em> <code>-1</code>. Nếu tồn tại lời giải, lời giải đó được <strong>đảm bảo</strong> là <strong>duy nhất</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> gas = [1,2,3,4,5], cost = [3,4,5,1,2]
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong>
Bắt đầu tại trạm 3 (chỉ số 3) và đổ đầy 4 đơn vị xăng. Bình xăng = 0 + 4 = 4
Di chuyển đến trạm 4. Bình xăng = 4 - 1 + 5 = 8
Di chuyển đến trạm 0. Bình xăng = 8 - 2 + 1 = 7
Di chuyển đến trạm 1. Bình xăng = 7 - 3 + 2 = 6
Di chuyển đến trạm 2. Bình xăng = 6 - 4 + 3 = 5
Di chuyển đến trạm 3. Chi phí là 5. Lượng xăng vừa đủ để quay lại trạm 3.
Do đó, trả về 3 là chỉ số bắt đầu.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> gas = [2,3,4], cost = [3,4,3]
<strong>Đầu ra:</strong> -1
<strong>Giải thích:</strong>
Bạn không thể bắt đầu tại trạm 0 hoặc 1 vì không có đủ xăng để đi đến trạm kế tiếp.
Hãy bắt đầu tại trạm 2 và đổ đầy 4 đơn vị xăng. Bình xăng = 0 + 4 = 4
Di chuyển đến trạm 0. Bình xăng = 4 - 3 + 2 = 3
Di chuyển đến trạm 1. Bình xăng = 3 - 3 + 3 = 3
Bạn không thể quay lại trạm 2 vì cần 4 đơn vị xăng nhưng bạn chỉ có 3.
Do đó, bạn không thể đi quanh mạch một lần dù bắt đầu ở đâu.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>n == gas.length == cost.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= gas[i], cost[i] &lt;= 10<sup>4</sup></code></li>
	<li>Đầu vào được tạo sao cho đáp án là duy nhất.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta cần một trạm bắt đầu có thể hoàn thành vòng lặp, nếu có. Vì $n\le 10^5$, mô phỏng từ mọi điểm bắt đầu sẽ có độ phức tạp $O(n^2)$. Nếu tổng lượng xăng nhỏ hơn tổng chi phí thì không có đáp án.
>
> Khi bình xăng âm, điểm bắt đầu phải lùi về trước để bù phần thiếu hụt đó. Code mở rộng phần cuối về phía trước và điểm bắt đầu về phía sau từ trạm cuối, dịch $i$ sang trái bất cứ khi nào bình xăng giảm xuống dưới không. Mỗi trạm chỉ được sử dụng một lần; nếu bình xăng không âm ở cuối, đó là điểm bắt đầu.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        n = len(gas)
        i = j = n - 1
        cnt = s = 0
        while cnt < n:
            s += gas[j] - cost[j]
            cnt += 1
            j = (j + 1) % n
            while s < 0 and cnt < n:
                i -= 1
                s += gas[i] - cost[i]
                cnt += 1
        return -1 if s < 0 else i
```

#### Java

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = n - 1, j = n - 1;
        int cnt = 0, s = 0;
        while (cnt < n) {
            s += gas[j] - cost[j];
            ++cnt;
            j = (j + 1) % n;
            while (s < 0 && cnt < n) {
                --i;
                s += gas[i] - cost[i];
                ++cnt;
            }
        }
        return s < 0 ? -1 : i;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int n = gas.size();
        int i = n - 1, j = n - 1;
        int cnt = 0, s = 0;
        while (cnt < n) {
            s += gas[j] - cost[j];
            ++cnt;
            j = (j + 1) % n;
            while (s < 0 && cnt < n) {
                --i;
                s += gas[i] - cost[i];
                ++cnt;
            }
        }
        return s < 0 ? -1 : i;
    }
};
```

#### Go

```go
func canCompleteCircuit(gas []int, cost []int) int {
	n := len(gas)
	i, j := n-1, n-1
	cnt, s := 0, 0
	for cnt < n {
		s += gas[j] - cost[j]
		cnt++
		j = (j + 1) % n
		for s < 0 && cnt < n {
			i--
			s += gas[i] - cost[i]
			cnt++
		}
	}
	if s < 0 {
		return -1
	}
	return i
}
```

#### TypeScript

```ts
function canCompleteCircuit(gas: number[], cost: number[]): number {
    const n = gas.length;
    let i = n - 1;
    let j = n - 1;
    let s = 0;
    let cnt = 0;
    while (cnt < n) {
        s += gas[j] - cost[j];
        ++cnt;
        j = (j + 1) % n;
        while (s < 0 && cnt < n) {
            --i;
            s += gas[i] - cost[i];
            ++cnt;
        }
    }
    return s < 0 ? -1 : i;
}
```

#### C#

```cs
public class Solution {
    public int CanCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.Length;
        int i = n - 1, j = n - 1;
        int s = 0, cnt = 0;
        while (cnt < n) {
            s += gas[j] - cost[j];
            ++cnt;
            j = (j + 1) % n;
            while (s < 0 && cnt < n) {
                --i;
                s += gas[i] - cost[i];
                ++cnt;
            }
        }
        return s < 0 ? -1 : i;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
