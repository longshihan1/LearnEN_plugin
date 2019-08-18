package com.longshihan.learnEN2.model;

import java.util.List;

public  class WordsBeanX {

    private WordBean word;

    public WordBean getWord() {
        return word;
    }

    public void setWord(WordBean word) {
        this.word = word;
    }

    public static class WordBean {

        private String wordHead;
        private String wordId;
        private ContentBean content;

        public String getWordHead() {
            return wordHead;
        }

        public void setWordHead(String wordHead) {
            this.wordHead = wordHead;
        }

        public String getWordId() {
            return wordId;
        }

        public void setWordId(String wordId) {
            this.wordId = wordId;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public static class ContentBean {

            private SentenceBean sentence;
            private String usphone;
            private String ukspeech;
            private int star;
            private String usspeech;
            private SynoBean syno;
            private String ukphone;
            private PhraseBean phrase;
            private String phone;
            private String speech;
            private RemMethodBean remMethod;
            private RelWordBean relWord;
            private List<TransBean> trans;

            public SentenceBean getSentence() {
                return sentence;
            }

            public void setSentence(SentenceBean sentence) {
                this.sentence = sentence;
            }

            public String getUsphone() {
                return usphone;
            }

            public void setUsphone(String usphone) {
                this.usphone = usphone;
            }

            public String getUkspeech() {
                return ukspeech;
            }

            public void setUkspeech(String ukspeech) {
                this.ukspeech = ukspeech;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public String getUsspeech() {
                return usspeech;
            }

            public void setUsspeech(String usspeech) {
                this.usspeech = usspeech;
            }

            public SynoBean getSyno() {
                return syno;
            }

            public void setSyno(SynoBean syno) {
                this.syno = syno;
            }

            public String getUkphone() {
                return ukphone;
            }

            public void setUkphone(String ukphone) {
                this.ukphone = ukphone;
            }

            public PhraseBean getPhrase() {
                return phrase;
            }

            public void setPhrase(PhraseBean phrase) {
                this.phrase = phrase;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getSpeech() {
                return speech;
            }

            public void setSpeech(String speech) {
                this.speech = speech;
            }

            public RemMethodBean getRemMethod() {
                return remMethod;
            }

            public void setRemMethod(RemMethodBean remMethod) {
                this.remMethod = remMethod;
            }

            public RelWordBean getRelWord() {
                return relWord;
            }

            public void setRelWord(RelWordBean relWord) {
                this.relWord = relWord;
            }

            public List<TransBean> getTrans() {
                return trans;
            }

            public void setTrans(List<TransBean> trans) {
                this.trans = trans;
            }

            public static class SentenceBean {
                /**
                 * sentences : [{"sContent":"an all-night pharmacy","sCn":"通宵药店"}]
                 * desc : 例句
                 */

                private String desc;
                private List<SentencesBean> sentences;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public List<SentencesBean> getSentences() {
                    return sentences;
                }

                public void setSentences(List<SentencesBean> sentences) {
                    this.sentences = sentences;
                }

                public static class SentencesBean {
                    /**
                     * sContent : an all-night pharmacy
                     * sCn : 通宵药店
                     */

                    private String sContent;
                    private String sCn;

                    public String getSContent() {
                        return sContent;
                    }

                    public void setSContent(String sContent) {
                        this.sContent = sContent;
                    }

                    public String getSCn() {
                        return sCn;
                    }

                    public void setSCn(String sCn) {
                        this.sCn = sCn;
                    }
                }
            }

            public static class SynoBean {
                /**
                 * synos : [{"pos":"n","tran":"药房；[药]配药学，药剂学；制药业；一批备用药品","hwds":[{"w":"dispensary"},{"w":"officina"}]}]
                 * desc : 同近
                 */

                private String desc;
                private List<SynosBean> synos;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public List<SynosBean> getSynos() {
                    return synos;
                }

                public void setSynos(List<SynosBean> synos) {
                    this.synos = synos;
                }

                public static class SynosBean {
                    /**
                     * pos : n
                     * tran : 药房；[药]配药学，药剂学；制药业；一批备用药品
                     * hwds : [{"w":"dispensary"},{"w":"officina"}]
                     */

                    private String pos;
                    private String tran;
                    private List<HwdsBean> hwds;

                    public String getPos() {
                        return pos;
                    }

                    public void setPos(String pos) {
                        this.pos = pos;
                    }

                    public String getTran() {
                        return tran;
                    }

                    public void setTran(String tran) {
                        this.tran = tran;
                    }

                    public List<HwdsBean> getHwds() {
                        return hwds;
                    }

                    public void setHwds(List<HwdsBean> hwds) {
                        this.hwds = hwds;
                    }

                    public static class HwdsBean {
                        /**
                         * w : dispensary
                         */

                        private String w;

                        public String getW() {
                            return w;
                        }

                        public void setW(String w) {
                            this.w = w;
                        }
                    }
                }
            }

            public static class PhraseBean {
                /**
                 * phrases : [{"pContent":"college of pharmacy","pCn":"药学院；药剂学院"},{"pContent":"pharmacy equipment","pCn":"药房设备"}]
                 * desc : 短语
                 */

                private String desc;
                private List<PhrasesBean> phrases;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public List<PhrasesBean> getPhrases() {
                    return phrases;
                }

                public void setPhrases(List<PhrasesBean> phrases) {
                    this.phrases = phrases;
                }

                public static class PhrasesBean {
                    /**
                     * pContent : college of pharmacy
                     * pCn : 药学院；药剂学院
                     */

                    private String pContent;
                    private String pCn;

                    public String getPContent() {
                        return pContent;
                    }

                    public void setPContent(String pContent) {
                        this.pContent = pContent;
                    }

                    public String getPCn() {
                        return pCn;
                    }

                    public void setPCn(String pCn) {
                        this.pCn = pCn;
                    }
                }
            }

            public static class RemMethodBean {
                /**
                 * val :  pharma(药； 毒) + cy → 药店； 药剂学
                 * desc : 记忆
                 */

                private String val;
                private String desc;

                public String getVal() {
                    return val;
                }

                public void setVal(String val) {
                    this.val = val;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }

            public static class RelWordBean {
                /**
                 * rels : [{"pos":"n","words":[{"hwd":"pharmacist","tran":" 药剂师"},{"hwd":"pharmacopoeia","tran":" 药典，处方书；一批备用药品"},{"hwd":"pharmaceutics","tran":" 制药学；配药学"}]}]
                 * desc : 同根
                 */

                private String desc;
                private List<RelsBean> rels;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public List<RelsBean> getRels() {
                    return rels;
                }

                public void setRels(List<RelsBean> rels) {
                    this.rels = rels;
                }

                public static class RelsBean {
                    /**
                     * pos : n
                     * words : [{"hwd":"pharmacist","tran":" 药剂师"},{"hwd":"pharmacopoeia","tran":" 药典，处方书；一批备用药品"},{"hwd":"pharmaceutics","tran":" 制药学；配药学"}]
                     */

                    private String pos;
                    private List<WordsBean> words;

                    public String getPos() {
                        return pos;
                    }

                    public void setPos(String pos) {
                        this.pos = pos;
                    }

                    public List<WordsBean> getWords() {
                        return words;
                    }

                    public void setWords(List<WordsBean> words) {
                        this.words = words;
                    }

                    public static class WordsBean {
                        /**
                         * hwd : pharmacist
                         * tran :  药剂师
                         */

                        private String hwd;
                        private String tran;

                        public String getHwd() {
                            return hwd;
                        }

                        public void setHwd(String hwd) {
                            this.hwd = hwd;
                        }

                        public String getTran() {
                            return tran;
                        }

                        public void setTran(String tran) {
                            this.tran = tran;
                        }
                    }
                }
            }

            public static class TransBean {
                /**
                 * tranCn : 药房；配药学，药剂学；制药业；一批备用药品
                 * descOther : 英释
                 * descCn : 中释
                 * pos : n
                 * tranOther : a shop or a part of a shop where medicines are prepared and sold
                 */

                private String tranCn;
                private String descOther;
                private String descCn;
                private String pos;
                private String tranOther;

                public String getTranCn() {
                    return tranCn;
                }

                public void setTranCn(String tranCn) {
                    this.tranCn = tranCn;
                }

                public String getDescOther() {
                    return descOther;
                }

                public void setDescOther(String descOther) {
                    this.descOther = descOther;
                }

                public String getDescCn() {
                    return descCn;
                }

                public void setDescCn(String descCn) {
                    this.descCn = descCn;
                }

                public String getPos() {
                    return pos;
                }

                public void setPos(String pos) {
                    this.pos = pos;
                }

                public String getTranOther() {
                    return tranOther;
                }

                public void setTranOther(String tranOther) {
                    this.tranOther = tranOther;
                }
            }
        }
    }
}
