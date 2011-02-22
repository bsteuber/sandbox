(ns io.mass-wget
  (:use (clojure.java io shell)))

(def lines 42)

(defn get-lines [path]
  (with-open [rdr (reader path)]
    (doall (line-seq rdr))))

(defn extract-url [line]
  (second
   (re-find #"(http:.*)}" line)))

(defn extract-urls [path]
  (->> path
       get-lines
       (map extract-url)
       (remove nil?)))

(def bib-file "/Users/ben/magicl/doc/diplomarbeit.bib")
(def sources-dir "/Users/ben/magicl/quellen/")

(def url-mapping
     {18 "http://msdn.microsoft.com/en-us/library/ee424598.aspx"
      15 "http://www.paulgraham.com/chameleon.html"
      40 "http://common-lisp.net/project/parenscript/tutorial.html"
      33 "http://www.cs.yale.edu/homes/dvm/papers/ytdoc.pdf"
      44 "http://www.w3.org/TR/2008/REC-xml-20081126"
      36 "http://www.cs.usfca.edu/~parrt/papers/mvc.templates.pdf"
      7 "http://velocity.apache.org"
      1 "http://www.boost.org"
      2 "http://tinyurl.com/33u3hbw"
      4 "http://www.haskell.org/bz/thdoc.htm"
      43 "http://www.ctan.org/tex-archive/macros/latex/contrib/beamer/doc/beameruserguide.pdf"
      30 "http://research.microsoft.com/en-us/um/people/emeijer/Papers/Parsec.pdf"
      26 "http://jalopy.sourceforge.net/existing/manual.html"
      31 "http://www-formal.stanford.edu/jmc/history/lisp/node3.html"
      3 "http://www.haskell.org/ghc"
      5 "http://www.emacswiki.org/emacs/ParEdit"})

(defn save-url [dir id url]
  (let [ending (if (.endsWith url "pdf")
                 "pdf"
                 "html")]
    (println (:err (sh "wget" "-O" (str id "." ending) url)))))

(comment
  (save-url sources-dir
            15 "http://www.paulgraham.com/chameleon.html"))

