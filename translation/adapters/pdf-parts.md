# Source adapter: PDF parts

Chỉ nạp khi `source.kind: pdf-parts`. Domain và target format được chọn riêng; PDF không mặc định là sách Java.

## Nguồn và inventory

Bản PDF gốc đúng edition là authority về nội dung. Ghi SHA-256 từng file; thứ tự part; vị trí trang trong tài liệu; số trang PDF 1-based và page label in trên sách nếu khác. Nếu công cụ dùng index 0-based, ghi rõ phép đổi. Không dùng page label suy ra index ngầm.

Nếu chỉ nhận một tập con, ghi scope tập con; không tuyên bố toàn sách. Không tạo nội dung cho trang/part bị thiếu. Kiểm tra duplicate/overlap/gap bằng manifest thực, không chỉ tên file. Nếu được tự chia, ưu tiên semantic boundary và kích thước vừa đủ đọc/review; không hardcode 10 trang cho mọi nguồn.

## Đọc và extraction

Đọc toàn bộ owned part. Text extraction chỉ hỗ trợ; khi layout, code, table, figure, footnote hoặc ký tự không rõ, phải đối chiếu hình trang gốc. OCR chỉ là phương án sau cùng khi nguồn không có text dùng được; kết quả OCR vẫn phải kiểm chứng, không có quyền thay bản gốc.

Kiểm tra riêng ký tự dễ sai: `1/l/I`, `0/O`, quotes, `< >`, `&`, `|`, `::`, `->`, annotation, subscript/superscript, đơn vị, indentation và các cột trong bảng. Chỉ khôi phục hyphenation/line wrapping do layout khi nhìn nguồn xác nhận được. Không format lại listing theo sở thích.

## Phân loại content

Dịch prose/heading/caption/note/footnote và table cell mang ngôn ngữ tự nhiên. Bảo toàn code/output/identifier theo CODE. Running header/footer và số trang layout được loại khi xác minh là page furniture; không bỏ title/caption thật. Attribution, copyright notice hoặc reference có nghĩa không phải rác layout để tuỳ ý xoá.

Asset lấy từ nguồn giữ hình gốc, ghi mapping; không vẽ lại dữ liệu theo suy đoán. Không tạo đường dẫn asset không tồn tại. Nội dung ảnh bắt buộc mà không đọc được phải được report, không coi placeholder là dịch xong.

## Neighbor và boundary

Đọc phần cuối/trước và đầu/sau đủ để hiểu cấu trúc liên tục; 1–2 trang là khởi điểm, không phải giới hạn buộc bỏ context cần thiết. Neighbor là read-only, không thuộc output của worker hiện tại.

Kích hoạt `translation/workflows/boundary.md`. Lưu continuation và vị trí trong sidecar, không thêm nhãn kỹ thuật vào sách. Mỗi part có thể lưu code fragment trong fence cục bộ để xem, nhưng sidecar phải đánh dấu fence là wrapper; khi ghép, integrator tạo đúng một logical listing từ source đã xác minh.

Không tự hoàn thiện câu/code/table bị cắt. Final không chỉ `cat *.md`: phải ghép theo kế hoạch boundary, kiểm tra semantic order, duplicate/omission và mọi logical structure. Nếu công cụ không xem được hình cần thiết, report `blocked`, không đoán.
