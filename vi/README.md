# LeetCode Wiki — bản tiếng Việt

Thư mục này chứa bản dịch tiếng Việt của [doocs/leetcode](https://github.com/doocs/leetcode) và phần mở rộng để publish site riêng trên Vercel.

- Tiếng Việt: `/vi/`.
- 中文 và English trong language switch mở đúng bài trên site gốc.
- Source upstream không bị sửa; bản dịch nằm riêng trong `vi/`.

## Dịch một bài

1. Đọc:
   - `translation/RULE.md`
   - `translation/INSTRUCTIONS.md`
   - `translation/PROJECT_RULES.md`
   - `translation/GLOSSARY.md`
   - `README_EN.md` của bài.
2. Tạo hoặc update `vi/<đường dẫn bài>/README.md`.
3. Dịch prose theo nguyên tắc: **đúng meaning, tiếng Việt tự nhiên, giữ English technical term khi phù hợp**.
4. Giữ nguyên front matter, H1, code, inline code, LaTeX, URL, marker và dữ liệu ví dụ.
5. Chạy:

```bash
pnpm exec prettier --write "vi/solution/0000-0099/0001.Two Sum/README.md"
python3 translation/tools/check_vi.py "vi/solution/0000-0099/0001.Two Sum/README.md"
python3 scripts/check_thinking.py "vi/solution/0000-0099/0001.Two Sum/README.md"
```

6. Nếu pass, bài đã hoàn tất.

Không cần unit report, hash, inventory state, coverage map hoặc lifecycle review cho từng bài.

## Cấu trúc chính

| Đường dẫn | Nội dung |
| --- | --- |
| `vi/solution/<range>/<problem>/README.md` | Bản dịch của `README_EN.md` |
| `vi/lcci/<problem>/README.md` | Bản dịch CCI |
| `translation/RULE.md` | Rule dịch chính |
| `translation/INSTRUCTIONS.md` | Workflow |
| `translation/PROJECT_RULES.md` | Convention riêng của repo |
| `translation/GLOSSARY.md` | Guideline terminology |
| `translation/tools/check_vi.py` | Mechanical validation |
| `vi/site/` | Site overlay và Vercel build |

Bài có file dịch sẽ được publish. Bài chưa dịch hiển thị stub “Chưa có bản dịch tiếng Việt”.

## Xem trước

```bash
VI_ONLY=1,74,lcci/01.01 bash vi/site/vercel-build.sh
python3 -m http.server 8000 --directory site
```

Chạy test:

```bash
python3 vi/site/prepare.py --export-engine .preview/vi-engine
python3 -m unittest discover -s vi/site/tests
python3 -m unittest discover -s translation/tools/tests
```

## Đồng bộ upstream

Sync fork như bình thường. Nếu một `README_EN.md` thay đổi, review lại target tương ứng khi xử lý bài đó. Không duy trì hash/provenance report cho toàn bộ corpus.
