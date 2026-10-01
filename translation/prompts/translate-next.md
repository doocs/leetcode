# Prompt: Translate one complete page

Mỗi invocation của prompt này xử lý **đúng một bài hoàn chỉnh**.

## Input unit

Source là một file `README_EN.md` của một bài.

Target là file `vi/.../README.md` tương ứng.

## Contract

Agent phải:

1. đọc toàn bộ source page;
2. dịch toàn bộ prose cần dịch của page;
3. giữ nguyên code, code comments, identifiers, math, URL, front matter và các protected literal;
4. viết hoàn chỉnh target page;
5. quick-review source ↔ target;
6. chạy mechanical check cần thiết;
7. kết thúc task của bài đó.

## Không được chia nhỏ ownership

Không tạo task phụ kiểu:

- Translate comments 0004;
- Translate headings 0004;
- Translate description 0004;
- Translate solution 0004.

Nếu bài là 0004 thì agent được giao 0004 phải chịu trách nhiệm toàn bộ page 0004.

Code comments mặc định không dịch và không phải một translation unit riêng.

## Batch behavior

Khi có nhiều agent:

- mỗi agent nhận một problem/page khác nhau;
- không hai agent cùng sửa một target file;
- 20 agents = tối đa 20 pages song song;
- agent xong page nào thì trả page đó, không tạo report/hash/state.

Tuân thủ `translation/RULE.md`, `translation/PROJECT_RULES.md` và `translation/GLOSSARY.md`.
