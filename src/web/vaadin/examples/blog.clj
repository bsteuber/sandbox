(ns web.vaadin.examples.blog
  (:use (web.vaadin constants
                    examples
                    utils
                    widgets)))

(def posts (atom {1 {:title "First Entry"
                     :content "This is the first blog entry"
                     :posted "2011-02-21"}
                  2 {:title "Second Entry"
                     :content "dumdidum"
                     :posted "2011-02-22"}}))

(defn post-editor [parent title content post]
  (let [cancel-button (button :caption "Cancel")
        done-button   (button :caption "Done")
        buttons       (horizontal [cancel-button done-button])
        title-field   nil
        editor        (panel [buttons])
        on-cancel     (fn [_]
                        )
        on-done       (fn [_]
                        (replace-component parent editor post))]
    (add-click-listener cancel-button on-cancel)
    (add-click-listener done-button   on-done)
    editor)
  )


(defn editable-post [parent [id {:keys [title content posted]}]]
  (let [body          (panel :caption title
                             [(label :caption (str "posted on " posted))
                              (label :caption content
                                     :content-mode content-xhtml)])
        edit-button   (button :caption "Edit")
        delete-button (button :caption "Delete")
        buttons       (horizontal [edit-button delete-button])
        post          (panel [buttons body])
        editor        (post-editor parent title content post)

        on-edit       (fn [_]
                        (replace-component parent post editor))
        on-delete     (fn [_]
                        (swap! posts dissoc id)
                        (remove-component parent post))
        ]
    (add-click-listener edit-button   on-edit)
    (add-click-listener delete-button on-delete)
    post))

(def blog
  (let [page       (vertical)
        add-button (button :caption "Add Post")]
    (doseq [p @posts]
      (add-component page (editable-post page p)))
    (add-component page add-button)
    page))

(run-example "Blog" blog)
