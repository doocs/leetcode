# Quality gates

## QA-01 — Hai lớp không thay thế nhau

**Semantic review:** đối chiếu trực tiếp source với target cho completeness và meaning. **Mechanical/integration checks:** protected spans, markup, links, assets, route/build. Build thành công không chứng minh fidelity; đọc bản dịch trôi chảy không chứng minh đúng source.

Reviewer phải đọc toàn bộ scope được ghi trong report. Sampling chỉ được báo sampling, không nâng thành verified toàn unit/corpus. Review không dùng diff target đơn thuần: phải xem đủ source và context liên quan.

## QA-02 — Coverage theo đơn vị nghĩa

Lập coverage map cho heading/paragraph/list item/table/caption/note/code/footnote. Kiểm tra theo cả hai chiều: source → target để phát hiện thiếu; target → source để phát hiện thêm. Một câu có thể thành hai câu khi dịch tự nhiên; không đòi số câu hoặc độ dài tuyệt đối bằng nhau.

Report ghi rõ phạm vi bị loại có chủ ý, source locator và lý do đã duyệt. Source bị thiếu không được loại khỏi mẫu số để đạt 100%. Figure/table phức tạp phải kiểm tra nội dung và quan hệ, không chỉ đếm placeholder.

## QA-03 — Checklist semantic

Đối chiếu chủ thể, thao tác, đối tượng; condition/exception; negation; cause/effect; quantifier; recommendation strength; comparison; version/time; số liệu/đơn vị; tham chiếu; domain traps; code-prose relationship. Dùng knowledge để hiểu source, không để sửa source.

Mỗi finding phải có source locator, target locator, evidence ngắn, rule ID, mức độ và cách sửa tối thiểu. Không yêu cầu công khai chain-of-thought. Câu đã đúng thì giữ; không rewrite cả file vì sở thích.

## QA-04 — Severity và chặn hoàn thành

| Mức      | Ví dụ                                                                                     | Xử lý                                                                |
| -------- | ----------------------------------------------------------------------------------------- | -------------------------------------------------------------------- |
| critical | Bịa/thiếu nội dung, đảo phủ định, corrupt code/output, ghi vào source, mất phần khi merge | Chặn verified/release                                                |
| high     | Sai điều kiện/modal/quan hệ kỹ thuật, link chính bị 404, boundary gãy nghĩa               | Chặn verified/release                                                |
| medium   | Thuật ngữ không nhất quán nhưng nghĩa còn đúng, markup phụ lỗi                            | Sửa trước verified hoặc có waiver cụ thể được chủ project chấp thuận |
| low      | Sở thích văn phong không đổi nghĩa                                                        | Không rewrite không cần thiết; ghi nhận nếu hữu ích                  |

“Đã report lỗi” không đồng nghĩa “đã resolve”. Không dùng waiver để gọi missing content/corrupt code là bản dịch hoàn chỉnh; trường hợp người dùng chấp nhận nhận draft thì nhãn vẫn là partial/draft.

## QA-05 — Gate cho verified

Owned scope được dịch đủ; source/target hash hiện tại khớp bản đã review; semantic coverage full; không còn blocker critical/high; protected spans hợp lệ theo policy; structure/links/assets đạt hoặc n/a có lý do; boundary cần thiết đã review; không còn marker chưa giải quyết; source chưa bị sửa; mọi required check có evidence.

Trạng thái check chỉ gồm `pass`, `fail`, `not_run`, `not_applicable`. `not_applicable` cần lý do thật; thiếu công cụ không phải n/a. `not_run` của check bắt buộc chặn verified. Nếu cấu hình yêu cầu reviewer độc lập mà không có thì dừng ở translated/reviewing, không tự đổi mode.

## QA-06 — Kiểm tra ngôn ngữ còn sót

Quét prose có thể phát hiện chỗ chưa dịch. Không coi “không có chữ Hán/English” là tiêu chuẩn duy nhất. Phân loại hit ở prose, code, identifier, URL, tên riêng, legal ID, exact input token hoặc ảnh; dùng allowlist có locator/lý do/reviewer.

Không dịch token kỹ thuật chỉ để làm scan rỗng. Khi policy yêu cầu dịch comment thì check comment riêng; tránh bỏ cả code block khiến sót prose được phép dịch không bị phát hiện.

## QA-07 — Sau sửa phải kiểm lại

Thay đổi target làm evidence cũ cần được đánh giá lại. Sửa đầu/cuối unit phải review boundary bị ảnh hưởng; sửa glossary/link mapping phải tìm các unit phụ thuộc. Reviewer report phải gắn target hash/revision, không duyệt bản A rồi publish bản B. Không hạ threshold, bỏ assertion hoặc bỏ file lỗi khỏi scope để đạt pass.
