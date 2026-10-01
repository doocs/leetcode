# Domain profile: thuật toán và cấu trúc dữ liệu (LeetCode)

Nạp khi `domain_profiles` có file này. Áp cho đề bài LeetCode/CCI và phần lời giải đi kèm.

## Scope và context

Đề bài là đặc tả chính xác: điều kiện đầu vào, định nghĩa đầu ra, ràng buộc và ví dụ. Lời giải mô tả ý tưởng, các bước, độ phức tạp và code nhiều ngôn ngữ. Không sửa thuật toán, không đổi độ phức tạp, không chọn cách giải khác với code trong repo.

## Thuật ngữ (baseline, quyết định chi tiết ở `translation/GLOSSARY.md`)

Dùng thuật ngữ tiếng Việt đã ổn định trong giảng dạy và lập trình thi đấu (mảng, chuỗi, ma trận, danh sách liên kết, cây nhị phân, đồ thị, ngăn xếp, hàng đợi, bảng băm, tìm kiếm nhị phân, quy hoạch động, tham lam, quay lui, tổng tiền tố, cửa sổ trượt, hai con trỏ). Giữ tiếng Anh cho tên thường dùng nguyên dạng: DFS, BFS, heap, trie, union-find, bitmask, hash set.

## Bẫy khi review

- `subarray` (mảng con liên tiếp) ≠ `subsequence` (dãy con, không cần liên tiếp) ≠ `substring` (chuỗi con liên tiếp) ≠ `subset` (tập con).
- `non-decreasing` (không giảm) ≠ `increasing` (tăng); `strictly` phải được dịch.
- `at most` / `at least` / `exactly` / `distinct` / `unique` / `any order` / `0-indexed` / `1-indexed`.
- `return` trong đề bài là "trả về"; `may assume` là "có thể giả sử", không thành "đảm bảo".
- `lexicographically smallest`, `modulo 10^9 + 7`, `in-place`, `without using extra space`.
- Độ phức tạp: giữ nguyên biểu thức (`$O(n \log n)$`), dịch câu bao quanh, giữ "where $n$ is …" thành "trong đó $n$ là …".

## Vùng bảo vệ đặc thù

- Mọi `$...$`/`$$...$$`, `<code>…</code>`, `` `…` `` và `\textit{…}` giữ nguyên.
- Trong `<pre>` ví dụ: chỉ dịch nhãn (`Input:` → `Đầu vào:` …) và câu giải thích; phần dữ liệu sau nhãn `Input`/`Output` giữ nguyên từng ký tự, kể cả khoảng trắng và lỗi của nguồn.
- Ảnh minh hoạ giữ nguyên URL; `alt` rỗng giữ rỗng.
