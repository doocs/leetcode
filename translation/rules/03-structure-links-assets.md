# Cấu trúc, liên kết và asset

## STRUCT-01 — Giữ quan hệ, không chỉ giữ text

Giữ hierarchy heading, thứ tự section, paragraph boundaries, list type/nesting/numbering, table row-column association, note/warning, quotation, footnote và tham chiếu. Không chuyển bảng thành summary, tự thêm heading hoặc gộp paragraph để rút ngắn. Chỉ đổi representation khi format đích cần và có mapping đã duyệt, giữ đầy đủ nghĩa.

Dịch prose heading nhưng giữ ID/cross-reference ổn định hoặc anchor mapping đã xác minh. Không sửa số Item, Chapter, equation, figure hoặc giá trị để khớp suy đoán.

## STRUCT-02 — Markup có semantics

Giữ fence/info string, directive, macro, shortcode, component name/prop kỹ thuật, YAML key, include/xref target, interpolation, placeholder và escape có ý nghĩa. Đọc format adapter trước khi sửa. Frontmatter chỉ dịch field hiển thị trong allowlist; không mặc định toàn bộ YAML là prose.

Không dùng regex replace hàng loạt để “sửa YAML” hoặc xoá ký tự nguồn. Dùng parser đúng dialect khi có; lỗi của nguồn ghi riêng, không tự viết lại upstream.

## STRUCT-03 — Link dựa trên mapping

External URL giữ nguyên. Đích external không truy cập được không cho phép tự đổi URL; ghi finding theo scope, không coi đó là thiếu prose khi nội dung nguồn cần dịch vẫn đầy đủ. Với internal link, đầu tiên phân loại: relative document, root-relative route, same-page anchor, include, asset hoặc resource của framework. Chỉ rewrite đích khi link policy cho phép và có mapping sang target tồn tại/được công bố hợp lệ.

Mirror file tree không tự chứng minh root-relative URL, permalink hoặc slug đúng. Giữ relative link chỉ khi nó resolve đúng từ vị trí target; asset có thể cần đường dẫn khác để vẫn trỏ tới **cùng asset gốc**. Không prefix `/vi/` vào mọi URL. Không sửa URL bên trong protected code/config mẫu.

Khi heading dịch làm đổi generated slug, xác minh anchor bằng renderer và cập nhật mapped links hoặc dùng explicit anchor theo framework. Giữ query string/hash nếu đích hỗ trợ; không âm thầm xoá fragment bị hỏng.

## STRUCT-04 — Trang chưa dịch

Không tạo link giả, trang rỗng hoặc copy tiếng nguồn rồi đánh dấu đã dịch. `missing_translation` có thể là disabled link, nhãn rõ “bản gốc”, hoặc fallback được cấu hình. Fallback không được đột ngột đưa người đọc về home/ngôn ngữ gốc mà không giải thích. Không hạ link checker toàn cục để che lỗi.

## STRUCT-05 — Hình, bảng và text trong hình

Giữ ảnh gốc, quan hệ với caption, license/credit và đường dẫn có thể resolve. Caption/alt từ source được dịch. Nếu cần alt mới cho accessibility, ghi là thay đổi bổ sung tách biệt, không nhận là nguyên bản dịch của source không có alt.

Không invent hình hoặc vẽ lại diagram theo trí nhớ. Text trong hình chỉ localize khi scope cho phép và đọc được nguồn; dùng asset target riêng, không ghi đè ảnh gốc. Nếu giữ ảnh chứa tiếng nguồn theo policy, ghi ngoại lệ; nếu mục tiêu yêu cầu dịch toàn bộ text trong hình nhưng chưa làm được, không claim hoàn thành phần đó.

## STRUCT-06 — Không rò metadata

Coverage ID, owner, reviewer note, boundary marker, tiến độ và thông tin công cụ nằm ngoài target xuất bản. Không xoá HTML comment gốc chỉ vì trông giống metadata; chỉ loại marker của pipeline được nhận diện rõ. Attribution/notice gốc là content phải bảo toàn, không coi là rác trang.
