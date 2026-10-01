# LeetCode Wiki — bản tiếng Việt

Thư mục này chứa bản dịch tiếng Việt của [doocs/leetcode](https://github.com/doocs/leetcode) và phần mở rộng để xuất bản nó thành site riêng trên Vercel:

- Tiếng Việt: `/vi/` (root `/` chuyển hướng về `/vi/`).
- Mục 中文 và English của nút chọn ngôn ngữ mở đúng bài đang đọc trên site gốc [leetcode.doocs.org](https://leetcode.doocs.org). Site này không build lại hai bản đó.

Bản gốc 中文 và English không bị sửa: mọi file trong `solution/`, `lcci/`, `lcof/`… giữ nguyên như upstream và chỉ được dùng làm nguồn để dịch. Bản dịch nằm riêng trong `vi/`, mirror đúng đường dẫn của bài.

## Cấu trúc

| Đường dẫn                              | Nội dung                                                                         |
| -------------------------------------- | -------------------------------------------------------------------------------- |
| `vi/solution/<khoảng>/<bài>/README.md` | Bản dịch của `solution/<khoảng>/<bài>/README_EN.md`                              |
| `vi/lcci/<bài>/README.md`              | Bản dịch của `lcci/<bài>/README_EN.md`                                           |
| `vi/site/ENGINE_REF`                   | Commit nhánh `docs` của doocs/leetcode dùng làm site engine                      |
| `vi/site/prepare.py`                   | Dựng thư mục build: engine upstream + cây `docs-vi/` + `mkdocs-site-vi.yml`      |
| `vi/site/build_vi.py`                  | Sinh `docs-vi/`: bài đã dịch hoặc trang "chưa dịch", nav, trang mục lục          |
| `vi/site/hooks/vi_switch.py`           | Nút chọn ngôn ngữ: 中文 / English mở đúng bài trên leetcode.doocs.org            |
| `vi/site/hooks/vi_markdown.py`         | Hiển thị trang vi: badge, khối "Tư duy", ghi chú bản dịch cũ, `noindex` cho stub |
| `vi/site/hooks/fork_site.py`           | Tắt bình luận giscus (đang gắn với Discussions của doocs) trên site của fork     |
| `vi/site/overrides/vi_stub.html`       | Template gọn cho trang "chưa dịch" (~3 KB/trang)                                 |
| `vi/site/docs-vi/`                     | Trang chủ, trang tags, trang contest của site vi                                 |
| `vi/site/vercel-build.sh`              | Lệnh build của Vercel: build site vi vào `site/vi/`                              |
| `vi/site/vercel-ignore.sh`             | Bỏ qua build khi không phải `main` hoặc không có thay đổi liên quan              |
| `vercel.json`                          | Cấu hình project Vercel                                                          |
| `.github/workflows/vi-site.yml`        | Test, kiểm tra bản dịch và build thử site vi trên PR/push                        |
| `translation/`                         | Bộ quy tắc dịch, glossary, công cụ kiểm tra và trạng thái từng bài               |

Mọi bài có bản English đều có trang trong `/vi/`. Bài chưa dịch hiển thị thông báo "Chưa có bản dịch tiếng Việt" và link tới bản English/中文 trên site gốc. Trang này không nằm trong nav, sitemap và kết quả tìm kiếm, và được gắn `noindex`. Trang mục lục của từng khoảng 100 bài vẫn liệt kê đủ mọi bài, bài đã dịch có dấu ✅.

## Dịch một bài

1. Đọc `translation/INSTRUCTIONS.md` và read set ở đó (đặc biệt `translation/PROJECT_RULES.md`, `translation/GLOSSARY.md`, `translation/domains/algorithms.md`).
2. Chọn bài trong `translation/state/PROGRESS.md` (sinh bằng `python3 translation/tools/inventory.py`).
3. Tạo `vi/<thư mục bài>/README.md` từ `README_EN.md`: dịch prose, giữ nguyên front matter, tiêu đề H1, code, công thức, marker `<!-- ... -->`, link và dữ liệu ví dụ.
4. Format bằng prettier, rồi kiểm tra:

    ```bash
    pnpm exec prettier --write "vi/solution/0000-0099/0001.Two Sum/README.md"
    python3 translation/tools/check_vi.py "vi/solution/0000-0099/0001.Two Sum/README.md"
    python3 scripts/check_thinking.py "vi/solution/0000-0099/0001.Two Sum/README.md"
    ```

5. Review hai chiều nguồn ↔ bản dịch, ghi report `translation/state/units/<unit id>.yaml`. Report gồm `git_blob` và `sha256` của nguồn và bản dịch, và phải được ghi sau khi format. Sau đó chạy `python3 translation/tools/inventory.py` để cập nhật tiến độ.
6. Mở PR. Workflow `vi-site` chạy test, `check_vi.py --all` và `inventory.py --check`.

Khi build, bản dịch chỉ được xuất bản nếu report là `verified` và hash khớp file hiện tại; nếu không, site hiển thị trang "chưa dịch". Nếu `README_EN.md` đổi sau khi review, bản dịch vẫn được xuất bản kèm ghi chú "Bản dịch có thể đã cũ".

## Xem trước trên máy

Cần Python 3.12+ và kết nối mạng (để lấy site engine và thư viện).

```bash
VI_ONLY=1,74,lcci/01.01 bash vi/site/vercel-build.sh   # bỏ VI_ONLY để build toàn bộ (~1 phút)
python3 -m http.server 8000 --directory site            # mở http://127.0.0.1:8000/vi/
```

Thư mục `.preview/` và `site/` đã được `.gitignore`. Chạy test (cần `pyyaml` và `beautifulsoup4`):

```bash
python3 vi/site/prepare.py --export-engine .preview/vi-engine
python3 -m unittest discover -s vi/site/tests
python3 -m unittest discover -s translation/tools/tests
```

## Deploy lên Vercel

Thiết lập một lần:

1. Trên Vercel: **Add New → Project → Import** repo `vandunxg/leetcode`. Giữ Framework Preset "Other". Build, output và redirect `/` → `/vi/` đã khai báo trong `vercel.json`, nên không cần nhập gì thêm.
2. Tuỳ chọn, trong **Settings → Environment Variables**:
    - `SITE_URL`: URL đầy đủ nếu dùng custom domain. Mặc định là domain production của project.
    - `UPSTREAM_SITE`: site được mở bởi mục 中文/English. Mặc định `https://leetcode.doocs.org`.
3. Trên GitHub, tab **Actions**: tắt các workflow upstream trỏ tới hạ tầng doocs: `deploy`, `deploy-request`, `sync-gitee`, `publish-gitee`. Chúng luôn fail trên fork (không có nhánh `docs`, không có secret của doocs).

Sau đó, mỗi lần push lên `main` có thay đổi trong `vi/`, `solution/`, `lcci/`, `translation/state/units/` hoặc `vercel.json`, Vercel sẽ build và deploy lại. Một lần build mất khoảng 2–3 phút; site nặng khoảng 40 MB. Nhánh khác `main` không được build.

## Đồng bộ với upstream

- Sync fork như bình thường. Phần tiếng Việt hầu như chỉ thêm file mới; xung đột chỉ có thể xảy ra ở `AGENTS.md` (mục "Translation tasks") và `scripts/check_thinking.py` (nhận heading `Lời giải`, nhãn `Tư duy`).
- Sau khi sync, `python3 translation/tools/inventory.py` đánh dấu `stale` các bài có `README_EN.md` đã đổi. Review lại theo `translation/workflows/upstream-sync.md`.
- Site engine được pin trong `vi/site/ENGINE_REF`. Khi muốn lấy engine mới của upstream: đổi SHA, chạy test và build thử, rồi commit.
