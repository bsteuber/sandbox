(ns web.vaadin.examples.blog
  (:use (web.vaadin constants
                    examples
                    listeners
                    widgets)))

(def posts (atom {1 {:title "First Entry"
                     :content "This is the first blog entry"
                     :posted "2011-02-21"}
                  2 {:title "Second Entry"
                     :content "dumdidum"
                     :posted "2011-02-22"}}))

(defn editable-post [[id {:keys [title content posted]}]]
  (let [on-edit       #()
        on-done       #()
        on-cancel     #()
        on-delete     #()
        edit-button   (button :caption "Edit")
        done-button   (button :caption "Done")
        cancel-button (button :caption "Cancel")
        delete-button (button :caption "Delete")
        header        (horizontal-layout [edit-button delete-button])
        body          (label :caption content
                             :content-mode content-xhtml)
        footer        (label :caption (str "posted on " posted))
        post          (panel :caption title
                             [header body footer])]
    post))

(def blog
  (let [editor rich-text-area]
    (vertical-layout (map editable-post @posts))))

(run-example "Blog" blog)