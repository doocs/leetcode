from mkdocs import plugins


# https://www.mkdocs.org/dev-guide/plugins/#events


@plugins.event_priority(90)
def on_post_page(output, page, config):
    difficulty = page.meta.get('difficulty')
    rating = page.meta.get('rating')

    # 在 h1 标题之后插入难度和评分信息及其样式
    html = str(output)
    # if difficulty:
    #     # 找到 h1 标签的结束位置
    #     h1_end = html.find('</h1>') + 5
    #     # 在 h1 标签之后插入难度信息
    #     html = html[:h1_end] + f'<p>Difficulty: {difficulty}</p>' + html[h1_end:]
    # if rating:
    #     # 找到 h1 标签的结束位置
    #     h1_end = html.find('</h1>') + 5
    #     # 在 h1 标签之后插入评分信息
    #     html = html[:h1_end] + f'<p>Rating: {rating}</p>' + html[h1_end:]
    return html