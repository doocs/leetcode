# Rule bổ sung theo project

File này không chép lại core và không tự cấp quyền vượt core. Các quyết định dưới đây áp dụng cho mirror tiếng Việt của `doocs/leetcode` (fork `vandunxg/leetcode`), được người dùng duyệt trong phiên 01/10/2026 (bootstrap + pilot).

## Context chuyên biệt

- Nguồn là README tiếng Anh của từng bài (`README_EN.md`), không phải bản tiếng Trung. Mỗi README gồm: front matter, tiêu đề, dòng chuyển phiên bản, phần đề bài viết bằng HTML (`<p>`, `<pre>`, `<ul>`, `<code>`, `<sup>`…), phần lời giải bằng Markdown + LaTeX (`$...$`), khối Thinking tuỳ chọn và các tab code nhiều ngôn ngữ.
- Giọng văn nguồn: phần đề bài là đề LeetCode; phần lời giải dùng ngôi "we". Bản dịch giữ ngôi "chúng ta" khi nguồn dùng "we", không tự thêm ngôi khi nguồn không có.
- Domain profile: `translation/domains/algorithms.md`.

## Quyết định có phạm vi

### PROJECT-001 — Tiêu đề bài giữ tên chính thức

- Áp dụng: dòng H1 `# [<số>. <tên>](<link>)` của mọi unit; nav và trang mục lục của site vi.
- Policy liên quan: STYLE-03 (tên riêng/nhãn định danh), `policies.language_exceptions`.
- Quyết định: giữ nguyên dòng H1 của nguồn (tên bài tiếng Anh chính thức). Không dịch, không thêm tên tiếng Việt trong ngoặc.
- Căn cứ: quyết định D1 của người dùng; tên bài là định danh dùng để tra trên LeetCode và giữ nhất quán giữa trang stub và trang đã dịch.
- Kiểm tra: `check_vi.py` (`h1`).

### PROJECT-002 — Front matter và code giữ nguyên, trừ comment giải thích

- Áp dụng: toàn bộ front matter (`comments`, `difficulty`, `tags`, `rating`, `source`, …), mọi code fence, các file `Solution*.*`.
- Policy liên quan: CODE-01, STRUCT-02, `markup_text_allowlist: []`.
- Quyết định: front matter giống từng byte với nguồn; giá trị `difficulty`/`source` chỉ được site hiển thị bằng nhãn tiếng Việt (`vi/site/hooks/vi_markdown.py`), không sửa trong file. Comment giải thích trong code được dịch; shebang, directive, output và token kỹ thuật trong comment vẫn giữ nguyên (`code_comments: translate_explanatory`).
- Kiểm tra: `check_vi.py` (`frontmatter`, code fence sau khi loại comment giải thích), `check_thinking.py`.

### PROJECT-003 — Nhãn cấu trúc cố định

- Áp dụng: heading và nhãn lặp lại trong mọi README.
- Quyết định (dùng đúng chuỗi sau, không biến thể):

    | Nguồn                                     | Bản dịch                               |
    | ----------------------------------------- | -------------------------------------- |
    | `## Description`                          | `## Mô tả`                             |
    | `## Solutions`                            | `## Lời giải`                          |
    | `### Solution N: <tên cách>`              | `### Lời giải N: <tên cách đã dịch>`   |
    | `> **Thinking**`                          | `> **Tư duy**`                         |
    | `Example N:`                              | `Ví dụ N:`                             |
    | `Input:` / `Output:` / `Explanation:`     | `Đầu vào:` / `Đầu ra:` / `Giải thích:` |
    | `Constraints:`                            | `Ràng buộc:`                           |
    | `Note:`                                   | `Lưu ý:`                               |
    | `Follow-up:`                              | `Câu hỏi mở rộng:`                     |
    | `#### Python3`, `#### Java`, … (tab code) | giữ nguyên                             |

- Căn cứ: `scripts/check_thinking.py` nhận heading `Lời giải` và nhãn `Tư duy`; `vi_markdown.py` đổi khối `Tư duy` thành admonition; tab code là nhãn kỹ thuật của site engine.
- Kiểm tra: `check_vi.py` (`headings`, `tabs`), `python3 scripts/check_thinking.py <target>`.

### PROJECT-004 — Link nội bộ và dòng chuyển phiên bản

- Áp dụng: link `/solution/.../README.md`, `/solution/.../README_EN.md`, `/lcci/...` và dòng `[中文文档](...)`.
- Policy liên quan: STRUCT-03, `internal_links: verified_mapping`.
- Quyết định: giữ nguyên chuỗi link và dòng chuyển phiên bản. Trên GitHub, link vẫn trỏ đúng bản gốc. Trên site, `ext_info.rewrite_repo_problem_links` (gọi từ `vi_markdown.py`) map link tới trang `/vi/lc/{num}/` tương ứng, còn dòng chuyển phiên bản bị bỏ như ở site zh/en.
- Kiểm tra: `check_vi.py` (`urls`), build site vi.

### PROJECT-005 — Chỉ bản verified vào `main`

- Áp dụng: mọi file `vi/**/README.md`.
- Quyết định: bản nháp nằm trên nhánh/PR. File chỉ vào `main` khi unit report `state: verified` khớp blob nguồn và đích hiện tại. Site coi mọi file có mặt là bản dịch xuất bản.
- Căn cứ: quyết định D4 của người dùng; QA-05, STATE-02.
- Kiểm tra: `python3 translation/tools/inventory.py --check` (CI `vi-site`). Khi build, `vi/site/build_vi.py` chỉ xuất bản file có report `verified` và `target.sha256` khớp; file khác được thay bằng trang stub. Report phải được ghi sau khi đã chạy prettier, vì định dạng lại cũng làm đổi hash.

### PROJECT-006 — Scope, source map và hash

- Áp dụng: inventory và unit report.
- Quyết định: scope là README tiếng Anh của `solution/` và `lcci/` theo `PROJECT_CONTEXT.yaml`. Source map sinh lại từ Git bằng `translation/tools/inventory.py` và không commit (`translation/state/.gitignore`); `PROGRESS.md` và unit report được commit. Hash dùng Git blob SHA-1 (`git ls-tree` cho nguồn tại commit, `git hash-object` cho đích), ghi rõ `kind: git-blob` trong report, thay cho trường `sha256` của template.
- Căn cứ: source map hoàn toàn suy ra được từ một commit; commit 4.177 dòng sẽ đổi mỗi lần sync upstream.
- Kiểm tra: `inventory.py` báo `stale` khi blob nguồn khác blob đã review.

## Ngoại lệ cần đặc biệt thận trọng

Dịch comment không đồng nghĩa được đổi identifier/string. Thay đường dẫn link để giữ cùng đích không đồng nghĩa được sửa URL trong đoạn code. Dịch label sơ đồ không đồng nghĩa được thay node ID hoặc quan hệ. Cho phép tạo site không đồng nghĩa được sửa source upstream.

Lỗi trong nguồn (ví dụ dữ liệu ví dụ thiếu tên biến) được giữ nguyên trong bản dịch và ghi vào unit report (`source_corrections: report_only`).
