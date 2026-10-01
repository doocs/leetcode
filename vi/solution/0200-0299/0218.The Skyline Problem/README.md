---
comments: true
difficulty: Hard
tags:
    - Binary Indexed Tree
    - Segment Tree
    - Array
    - Divide and Conquer
    - Ordered Set
    - Sorting
    - Sweep Line
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [218. The Skyline Problem](https://leetcode.com/problems/the-skyline-problem)

[中文文档](/solution/0200-0299/0218.The%20Skyline%20Problem/README.md)

## Mô tả

<!-- description:start -->

<p><strong>Đường chân trời</strong> của một thành phố là đường bao ngoài của hình bóng được tạo bởi tất cả các tòa nhà trong thành phố đó khi nhìn từ xa. Cho vị trí và chiều cao của tất cả các tòa nhà, hãy trả về <em><strong>đường chân trời</strong> được tạo bởi toàn bộ các tòa nhà đó</em>.</p>

<p>Thông tin hình học của mỗi tòa nhà được cho trong mảng <code>buildings</code>, trong đó <code>buildings[i] = [left<sub>i</sub>, right<sub>i</sub>, height<sub>i</sub>]</code>:</p>

<ul>
	<li><code>left<sub>i</sub></code> là tọa độ x của cạnh trái của tòa nhà thứ <code>i<sup>th</sup></code>.</li>
	<li><code>right<sub>i</sub></code> là tọa độ x của cạnh phải của tòa nhà thứ <code>i<sup>th</sup></code>.</li>
	<li><code>height<sub>i</sub></code> là chiều cao của tòa nhà thứ <code>i<sup>th</sup></code>.</li>
</ul>

<p>Bạn có thể giả sử rằng tất cả các tòa nhà là những hình chữ nhật hoàn hảo, nằm trên một bề mặt hoàn toàn phẳng ở độ cao <code>0</code>.</p>

<p><strong>Đường chân trời</strong> phải được biểu diễn dưới dạng một danh sách các "điểm then chốt" được <strong>sắp xếp theo tọa độ x</strong>, có dạng <code>[[x<sub>1</sub>,y<sub>1</sub>],[x<sub>2</sub>,y<sub>2</sub>],...]</code>. Mỗi điểm then chốt là điểm đầu bên trái của một đoạn nằm ngang nào đó trong đường chân trời, ngoại trừ điểm cuối cùng trong danh sách, điểm này luôn có tọa độ y bằng <code>0</code> và được dùng để đánh dấu điểm kết thúc của đường chân trời, tại nơi tòa nhà ngoài cùng bên phải kết thúc. Mọi phần mặt đất nằm giữa tòa nhà ngoài cùng bên trái và tòa nhà ngoài cùng bên phải đều phải là một phần của đường bao đường chân trời.</p>

<p><b>Lưu ý:</b> Trong đường chân trời đầu ra không được có các đoạn nằm ngang liên tiếp có cùng độ cao. Ví dụ, <code>[...,[2 3],[4 5],[7 5],[11 5],[12 7],...]</code> là không hợp lệ; ba đoạn có độ cao 5 phải được gộp thành một đoạn trong kết quả cuối cùng như sau: <code>[...,[2 3],[4 5],[12 7],...]</code></p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0218.The%20Skyline%20Problem/images/merged.jpg" style="width: 800px; height: 331px;" />
<pre>
<strong>Đầu vào:</strong> buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
<strong>Đầu ra:</strong> [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
<strong>Giải thích:</strong>
Hình A cho thấy các tòa nhà trong đầu vào.
Hình B cho thấy đường chân trời được tạo bởi các tòa nhà đó. Các điểm màu đỏ trong hình B biểu diễn các điểm then chốt trong danh sách đầu ra.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> buildings = [[0,2,3],[2,5,3]]
<strong>Đầu ra:</strong> [[0,3],[5,0]]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= buildings.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= left<sub>i</sub> &lt; right<sub>i</sub> &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>1 &lt;= height<sub>i</sub> &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>buildings</code> được sắp xếp theo <code>left<sub>i</sub></code> theo thứ tự không giảm.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Các tòa nhà che khuất lẫn nhau, vì vậy việc so sánh mọi chiều cao tại mọi $x$ là không khả thi. Đường chân trời chỉ có thể thay đổi tại các cạnh trái và phải, và các vị trí này trở thành các sự kiện quét.
>
> Chúng ta sắp xếp các tọa độ $x$ đó và duy trì một max-heap gồm các tòa nhà vẫn còn phủ lên đường quét. Một điểm then chốt được tạo ra khi chiều cao thay đổi; các chiều cao bằng nhau liền kề được gộp lại.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
from queue import PriorityQueue


class Solution:
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        skys, lines, pq = [], [], PriorityQueue()
        for build in buildings:
            lines.extend([build[0], build[1]])
        lines.sort()
        city, n = 0, len(buildings)
        for line in lines:
            while city < n and buildings[city][0] <= line:
                pq.put([-buildings[city][2], buildings[city][0], buildings[city][1]])
                city += 1
            while not pq.empty() and pq.queue[0][2] <= line:
                pq.get()
            high = 0
            if not pq.empty():
                high = -pq.queue[0][0]
            if len(skys) > 0 and skys[-1][1] == high:
                continue
            skys.append([line, high])
        return skys
```

#### C++

```cpp
class Solution {
public:
    vector<pair<int, int>> getSkyline(vector<vector<int>>& buildings) {
        set<int> poss;
        map<int, int> m;
        for (auto v : buildings) {
            poss.insert(v[0]);
            poss.insert(v[1]);
        }

        int i = 0;
        for (int pos : poss)
            m.insert(pair<int, int>(pos, i++));

        vector<int> highs(m.size(), 0);
        for (auto v : buildings) {
            const int b = m[v[0]], e = m[v[1]];
            for (int i = b; i < e; ++i)
                highs[i] = max(highs[i], v[2]);
        }

        vector<pair<int, int>> res;
        vector<int> mm(poss.begin(), poss.end());
        for (int i = 0; i < highs.size(); ++i) {
            if (highs[i] != highs[i + 1])
                res.push_back(pair<int, int>(mm[i], highs[i]));
            else {
                const int start = i;
                res.push_back(pair<int, int>(mm[start], highs[i]));
                while (highs[i] == highs[i + 1])
                    ++i;
            }
        }
        return res;
    }
};
```

#### Go

```go
type Matrix struct{ left, right, height int }
type Queue []Matrix

func (q Queue) Len() int           { return len(q) }
func (q Queue) Top() Matrix        { return q[0] }
func (q Queue) Swap(i, j int)      { q[i], q[j] = q[j], q[i] }
func (q Queue) Less(i, j int) bool { return q[i].height > q[j].height }
func (q *Queue) Push(x any)        { *q = append(*q, x.(Matrix)) }
func (q *Queue) Pop() any {
	old, x := *q, (*q)[len(*q)-1]
	*q = old[:len(old)-1]
	return x
}

func getSkyline(buildings [][]int) [][]int {
	skys, lines, pq := make([][]int, 0), make([]int, 0), &Queue{}
	heap.Init(pq)
	for _, v := range buildings {
		lines = append(lines, v[0], v[1])
	}
	sort.Ints(lines)
	city, n := 0, len(buildings)
	for _, line := range lines {
		// Thêm tất cả các hình chữ nhật thỏa mãn điều kiện vào hàng đợi
		for ; city < n && buildings[city][0] <= line && buildings[city][1] > line; city++ {
			v := Matrix{left: buildings[city][0], right: buildings[city][1], height: buildings[city][2]}
			heap.Push(pq, v)
		}
		// Xóa khỏi hàng đợi các hình chữ nhật không thỏa mãn điều kiện
		for pq.Len() > 0 && pq.Top().right <= line {
			heap.Pop(pq)
		}
		high := 0
		// Hàng đợi rỗng cho biết đây là điểm kết thúc của tòa nhà ngoài cùng bên phải, điểm đường bao của nó là (line, 0)
		if pq.Len() != 0 {
			high = pq.Top().height
		}
		// Nếu độ cao của điểm này giống với điểm đường bao trước đó thì bỏ qua
		if len(skys) > 0 && skys[len(skys)-1][1] == high {
			continue
		}
		skys = append(skys, []int{line, high})
	}
	return skys
}
```

#### Rust

```rust
impl Solution {
    pub fn get_skyline(buildings: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut skys: Vec<Vec<i32>> = vec![];
        let mut lines = vec![];
        for building in buildings.iter() {
            lines.push(building[0]);
            lines.push(building[1]);
        }
        lines.sort_unstable();
        let mut pq = std::collections::BinaryHeap::new();
        let (mut city, n) = (0, buildings.len());

        for line in lines {
            while city < n && buildings[city][0] <= line && buildings[city][1] > line {
                pq.push((buildings[city][2], buildings[city][1]));
                city += 1;
            }
            while !pq.is_empty() && pq.peek().unwrap().1 <= line {
                pq.pop();
            }
            let mut high = 0;
            if !pq.is_empty() {
                high = pq.peek().unwrap().0;
            }
            if !skys.is_empty() && skys.last().unwrap()[1] == high {
                continue;
            }
            skys.push(vec![line, high]);
        }
        skys
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
