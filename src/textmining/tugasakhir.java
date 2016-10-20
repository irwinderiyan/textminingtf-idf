/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import org.apache.lucene.analysis.id.IndonesianStemFilter;
import org.apache.lucene.analysis.id.IndonesianStemmer;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author ASUS
 */
public class tugasakhir extends javax.swing.JFrame {

    /**
     * Creates new form tugasakhir
     */
    String doc1, doc2, doc3, doc4, doc5;
    LinkedList hasilterm = new LinkedList();
    //Array list untuk proses stemming 

    ArrayList<String> dooc1 = new ArrayList<String>();
    ArrayList<String> repdoc1 = new ArrayList<String>();
    ArrayList<String> dooc2 = new ArrayList<String>();
    ArrayList<String> repdoc2 = new ArrayList<String>();
    ArrayList<String> dooc3 = new ArrayList<String>();
    ArrayList<String> repdoc3 = new ArrayList<String>();
    ArrayList<String> dooc4 = new ArrayList<String>();
    ArrayList<String> repdoc4 = new ArrayList<String>();
    ArrayList<String> dooc5 = new ArrayList<String>();
    ArrayList<String> repdoc5 = new ArrayList<String>();
    int pemicu = 0, pemicu2 = 0;
    String data[] = {
        "Term", "Doc 1", "Doc 2", "Doc 3", "Doc 4", "Doc 5", "IDF"
    };
    String[] lexical = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "`", ".",
        "~", "!", "@", "#", "$", "%", "^", "&", ";",
        "*", "(", ")", "_", "-", "=", "+", ":", "<", ">", "?", ",", "//", "'", "\"", "{", "}", "]", "["};
    String stopWords[] = {"janganlah", "jauh", "jawab", "jawaban", "jawabnya",
        "jelas", "jelaskan", "jelaslah", "jelasnya", "jika", "juga", "jumlah", "jumlahnya", "justru", "kala", "kalau", "kalaulah", "kalaupun",
        "kalian", "kami", "kamilah", "kamu", "kamulah", "kan", "kapan", "kapankah", "kapanpun", "kasus", "kata", "katakan", "katakanlah", "katanya", "ke",
        "keadaan", "kebetulan", "kecil", "kedua", "keduanya", "keinginan", "kelamaan", "kelihatan", "kelihatannya", "kelima", "keluar", "kembali",
        "kemudian", "kemungkinan", "kemungkinannya", "kepada", "kepadanya", "kesampaian", "keseluruhan", "keseluruhannya", "keterlaluan", "ketika",
        "khususnya", "kini", "kinilah", "kira", "kira-kira", "kiranya", "kita", "kitalah", "kurang", "lagi", "lagian", "lah", "lain", "lainnya", "lalu",
        "lama", "lamanya", "lanjut", "lanjutnya", "lebih", "lewat", "lima", "luar", "macam", "maka", "makanya", "makin", "malah", "malahan", "mampu",
        "mana", "manakala", "manalagi", "masa", "masalah", "masalahnya", "masih", "masihkah", "masing", "masing-masing", "mau", "maupun", "melainkan",
        "melakukan", "melalui", "melihat", "melihatnya", "memang", "memastikan", "memberi", "memberikan", "membuat", "memerlukan", "memihak", "meminta",
        "memintakan", "memisalkan", "memperbuat", "mempergunakan", "memperkirakan", "memperlihatkan", "mempersiapkan", "mempersoalkan", "mempertanyakan",
        "mempunyai", "memulai", "memungkinkan", "menaiki", "menambahkan", "menandaskan", "menanti", "menantikan", "menanti-nanti", "menanya", "menanyai",
        "menanyakan", "mendapat", "mendapatkan", "mendatang", "mendatangi", "mendatangkan", "menegaskan", "mengakhiri", "mengapa", "mengatakan",
        "mengatakannya", "mengenai", "mengerjakan", "mengetahui", "menggunakan", "menghendaki", "mengibaratkan", "mengibaratkannya", "mengingat",
        "mengingatkan", "menginginkan", "mengira", "mengucapkan", "mengucapkannya", "mengungkapkan", "menjadi", "menjawab", "menjelaskan", "menuju",
        "menunjuk", "menunjuki", "menunjukkan", "menunjuknya", "menurut", "menuturkan", "menyampaikan", "menyangkut", "menyatakan", "menyebutkan",
        "menyeluruh", "menyiapkan", "merasa", "mereka", "merekalah", "merupakan", "meski", "meskipun", "meyakini", "meyakinkan", "minta", "mirip",
        "misal", "misalkan", "misalnya", "mula", "mulai", "mulailah", "mulanya", "mungkinkah", "nah", "naik", "namun", "nanti", "nantinya", "nyaris",
        "nyatanya", "oleh", "olehnya", "pada", "padahal", "padanya", "pak", "paling", "panjang", "pantas", "para", "pasti", "penting", "pentingnya",
        "per", "percuma", "perlu", "perlukah", "perlunya", "pernah", "persoalan", "pertama", "pertama-tama", "pertanyakan", "pihak", "pihaknya", "pukul",
        "pula", "pun", "punya", "rasa", "rasanya", "rata", "rupanya", "saat", "saatnya", "saja", "sajalah", "saling", "sama", "sama-sama", "sambil",
        "sampai", "sampaikan", "sampai-sampai", "sana", "sangatlah", "satu", "saya", "sayalah", "se", "sebab", "sebabnya", "sebagaimana", "sebagainya",
        "sebagian", "sebaik", "sebaik-baiknya", "sebaiknya", "sebaliknya", "sebanyak", "sebegini", "sebelum", "sebelumnya", "sebenarnya", "seberapa",
        "sebesar", "sebetulnya", "sebisanya", "sebuah", "about", "adalah", "agak", "agar", "akan", "akibat", "akibatnya", "amatlah",
        "antara", "apa", "apa", "apabila", "apakah", "apalagi", "atau", "bagai", "bagaikan",
        "bagaimana", "bagi", "bahkan", "bahwa", "beginian", "begitu", "being", "belum", "berdatangan", "berlainan",
        "bersama", "betulkah", "biar", "biarpun", "bila", "bilamana", "bolehkah", "caranya",
        "contoh", "dalam", "dan", "dapat", "dari", "daripada", "demi", "demikian", "dengan", "di", "dikarenakan",
        "dimana", "dimungkinkan", "diperlukan", "dirinya", "eh", "hanya", "hanyalah", "hendaknya", "hingga", "ialah", "ibarat",
        "ini", "itu", "jadi", "jangan", "jika", "jikalau", "juga", "justru", "kalau", "karena",
        "karenanya", "ke", "kecuali", "kemudian", "kenapa", "kepada", "ketika", "kok", "lagi", "lagipula", "lainnya", "lalu", "maka",
        "mampukah", "masih", "maupun", "me", "melainkan", "memang", "meski", "meskipun", "misalnya",
        "mungkin", "namun", "oleh", "pada", "padahal", "paling", "pastilah", "pertanyaan", "pula", "pun", "re", "saat", "saja", "sambil", "sampai", "sangat",
        "sebab", "sebagai", "sebagai", "sebagaimana", "sebaliknya", "sebegitu", "sebelum", "sebuah", "sedang", "sedangkan", "sedari", "segera",
        "sehingga", "sejak", "sekalipun", "sekarang", "sekurangnya", "selain", "selama", "seluruhnya", "semakin", "semenjak", "sementara", "semisal",
        "sepanjang", "sepantasnyalah", "seperti", "sering", "serta", "sesudah", "setelah", "sudah",
        "supaya", "tanpa", "tapi", "telah", "tentang", "terhadap", "terlalu", "tersebut", "tersebutlah", "terus", "tetapi",
        "umpama", "untuk", "walau", "walaupun", "yaitu", "yakni", "ada", "adalah", "adanya", "adapun", "agaknya", "agar", "akan", "akankah", "akhir", "akhiri",
        "akhirnya", "aku", "akulah", "amat", "anda", "andalah", "antar", "antaranya", "apaan", "apatah", "artinya", "asal", "asalkan", "atas", "atau",
        "ataukah", "ataupun", "awal", "awalnya", "bagaimanakah", "bagaimanapun", "bagian", "bahkan", "bahwasanya", "baik", "bakal", "bakalan", "balik",
        "banyak", "bapak", "baru", "bawah", "beberapa", "begini", "beginikah", "beginilah", "begitu", "begitukah", "begitulah", "begitupun", "bekerja",
        "belakang", "belakangan", "belum", "belumlah", "benar", "benarkah", "benarlah", "berada", "berakhir", "berakhirlah", "berakhirnya", "berapa",
        "berapakah", "berapalah", "berapapun", "berarti", "berawal", "berbagai", "beri", "berikan", "berikut", "berikutnya", "berjumlah", "berkali-kali",
        "berkata", "berkehendak", "berkeinginan", "berkenaan", "berlalu", "berlangsung", "berlebihan", "bermacam", "bermacam-macam", "bermaksud", "bermula",
        "bersama", "bersama-sama", "bersiap", "bersiap-siap", "bertanya", "bertanya-tanya", "berturut", "berturut-", "bertutur", "berujar", "berupa", "besar",
        "betul", "biasa", "biasanya", "bila", "bilakah", "bisa", "bisakah", "boleh", "bolehlah", "buat", "bukan", "bukankah", "bukanlah", "bukannya", "bulan",
        "bung", "cara", "cukup", "cukupkah", "cukuplah", "cuma", "dahulu", "dalam", "dan", "dapat", "dari", "datang", "dekat", "demi", "demikian", "demikianlah",
        "dengan", "depan", "di", "dia", "diakhiri", "diakhirinya", "dialah", "diantara", "diantaranya", "diberi", "diberikan", "diberikannya", "dibuat", "dibuatnya",
        "didapat", "didatangkan", "digunakan", "diibaratkan", "diibaratkannya", "diingat", "diingatkan", "diinginkan", "dijawab", "dijelaskan", "dijelaskannya",
        "dikatakan", "dikatakannya", "dikerjakan", "diketahui", "diketahuinya", "dikira", "dilakukan", "dilalui", "dilihat", "dimaksud", "dimaksudkan",
        "dimaksudkannya", "dimaksudnya", "diminta", "dimintai", "dimisalkan", "dimulai", "dimulailah", "dimulainya", "dini", "dipastikan", "diperbuat", "diperbuatnya",
        "dipergunakan", "diperkirakan", "diperlihatkan", "diperlukannya", "dipersoalkan", "dipertanyakan", "dipunyai", "diri", "disampaikan", "disebut", "disebutkan",
        "disebutkannya", "disini", "disinilah", "ditambahkan", "ditandaskan", "ditanya", "ditanyai", "ditanyakan", "ditegaskan", "ditujukan", "ditunjuk", "ditunjuki",
        "ditunjukkan", "ditunjukkannya", "ditunjuknya", "dituturkan", "dituturkannya", "diucapkan", "diucapkannya", "diungkapkan", "dong", "dua", "dulu", "empat", "enggak",
        "enggaknya", "entah", "entahlah", "guna", "gunakan", "hal", "hampir", "hanya", "hari", "harus", "haruslah", "harusnya", "hendak", "hendaklah", "hingga", "ia",
        "ialah", "ibarat", "ibaratkan", "ibaratnya", "ibu", "if", "ikut", "ingat", "ingat-ingat", "ingin", "inginkah", "inginkan", "ini", "inikah",
        "inilah", "itu", "itukah", "itulah", "jadi", "jadilah", "jadinya", "jangan", "jangankan", "sebut", "sebutlah", "sebutnya", "secara", "secukupnya", "sedang", "sedangkan", "sedemikian",
        "sedikit", "sedikitnya", "seenaknya", "segala", "segalanya", "segera", "seharusnya", "sehingga", "seingat", "sejak", "sejauh", "sejenak",
        "sejumlah", "sekadar", "sekadarnya", "sekali", "sekalian", "sekaligus", "sekali-kali", "sekarang", "sekarang", "sekecil", "seketika", "sekiranya",
        "sekitar", "sekitarnya", "sekurang-kurangnya", "sela", "selain", "selaku", "selalu", "selama", "selama-lamanya", "selamanya", "selanjutnya",
        "seluruh", "semacam", "semakin", "semampu", "semampunya", "semasa", "semasih", "semata", "semata-mata", "semaunya", "sementara", "semisalnya",
        "sempat", "semua", "semuanya", "semula", "sendiri", "sendirian", "sendirinya", "seolah", "seolah-olah", "seorang", "sepantasnya", "seperlunya",
        "seperti", "sepertinya", "sepihak", "sering", "seringnya", "serta", "serupa", "ses udah", "sesaat", "sesama", "sesampai", "sesegera", "sesekali",
        "seseorang", "sesuatu", "sesuatunya", "sesudahnya", "setelah", "setempat", "setengah", "seterusnya", "setiap", "setiba", "setibanya", "setidaknya",
        "setidak-tidaknya", "setinggi", "seusai", "sewaktu", "siap", "siapa", "siapakah", "siapapun", "sini", "sinilah", "soal", "soalnya", "suatu",
        "sudahkah", "sudahlah", "supaya", "tadi", "tadinya", "tahu", "tahun", "tak", "tambah", "tambahnya", "tampak", "tampaknya", "tandas", "tandasnya",
        "tanpa", "tanya", "tanyakan", "tanyanya", "tapi", "tegas", "tegasnya", "tempat", "tengah", "tentu", "tentulah", "tentunya", "tepat", "terakhir",
        "terasa", "terbanyak", "terdahulu", "terdapat", "terdiri", "terhadap", "terhadapnya", "teringat", "teringat-ingat", "terjadi", "terjadilah",
        "terjadinya", "terkira", "terlebih", "terlihat", "termasuk", "ternyata", "tersampaikan", "tersebut", "tertentu", "tertuju", "terus", "terutama",
        "tetap", "tetapi", "tiap", "tiba", "tiba-tiba", "tidak", "tidakkah", "tidaklah", "tiga", "tinggi", "toh", "tunjuk", "turut", "tutur", "tuturnya",
        "ucap", "ucapnya", "ujar", "ujarnya", "umum", "umumnya", "ungkap", "ungkapnya", "untuk", "usah", "usai", "waduh", "wah", "wahai", "waktu", "waktunya", "walau", "walaupun", "wong", "yaitu", "yakin", "yakni", "yang"};

    ArrayList<String> wordsList1 = new ArrayList<String>();
    ArrayList<String> wordsList2 = new ArrayList<String>();
    ArrayList<String> wordsList3 = new ArrayList<String>();
    ArrayList<String> wordsList4 = new ArrayList<String>();
    ArrayList<String> wordsList5 = new ArrayList<String>();

    public tugasakhir() {
        initComponents();
      
        jLabel9.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The conte@nt of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jDialog1 = new javax.swing.JDialog();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jdoc1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jdoc2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jdoc3 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jdoc4 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jdoc5 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tugas Text Mining");

        jLabel1.setText("Document 1");

        jdoc1.setColumns(20);
        jdoc1.setRows(5);
        jScrollPane1.setViewportView(jdoc1);

        jLabel2.setText("Document 2");

        jdoc2.setColumns(20);
        jdoc2.setRows(5);
        jScrollPane2.setViewportView(jdoc2);

        jButton1.setText("tokenisasi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Document 3");

        jdoc3.setColumns(20);
        jdoc3.setRows(5);
        jScrollPane4.setViewportView(jdoc3);

        jLabel5.setText("Document 4");

        jdoc4.setColumns(20);
        jdoc4.setRows(5);
        jScrollPane5.setViewportView(jdoc4);

        jLabel6.setText("Document 5");

        jdoc5.setColumns(20);
        jdoc5.setRows(5);
        jScrollPane6.setViewportView(jdoc5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("parsing", jPanel1);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jButton3.setText("filter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Hasil Tokenisasi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(189, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tokenisasi", jPanel2);

        jLabel7.setText("Hasil Filtering :");

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane7.setViewportView(jTextArea4);

        jButton2.setText("stemming");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(188, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("filtering", jPanel3);

        jLabel8.setText("Hasil Stemming :");

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane8.setViewportView(jTextArea5);

        jButton4.setText("TF");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("stemming", jPanel4);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Term", "Doc 1", "Doc 2", "Doc 3", "Doc 4", "Doc 5"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTable1);

        jButton5.setText("Hitung Term Frequency");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("DF");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Hitung TF x IDF");
        jButton7.setEnabled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel9.setText("Selesai , Terima kasih .untuk daftar nama kelompok bisa klik menu file -> Nama kelompok");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton5)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("TF-IDF", jPanel5);

        jMenu4.setText("File");

        jMenuItem2.setText("Anggota Kelompok");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        doc1 = jdoc1.getText();
        doc2 = jdoc2.getText();
        doc3 = jdoc3.getText();
        doc4 = jdoc4.getText();
        doc5 = jdoc5.getText();

        doc1 = doc1.toLowerCase();
        doc2 = doc2.toLowerCase();
        doc3 = doc3.toLowerCase();
        doc4 = doc4.toLowerCase();
        doc5 = doc5.toLowerCase();

        //----menghapus tanda baca----
        String copydoc1[] = doc1.split("");
        for (int i = 0; i < copydoc1.length; i++) {
            for (int j = 0; j < lexical.length; j++) {
                if (copydoc1[i].equalsIgnoreCase(lexical[j])) {
                    copydoc1[i] = "";
                }
            }
        }
        doc1 = "";
        for (int i = 0; i < copydoc1.length; i++) {
            doc1 = doc1 + copydoc1[i];
        }

        //----menghapus tanda baca----
        //----menghapus tanda baca----
        String copydoc2[] = doc2.split("");
        for (int i = 0; i < copydoc2.length; i++) {
            for (int j = 0; j < lexical.length; j++) {
                if (copydoc2[i].equalsIgnoreCase(lexical[j])) {
                    copydoc2[i] = "";
                }
            }
        }
        doc2 = "";
        for (int i = 0; i < copydoc2.length; i++) {
            doc2 = doc2 + copydoc2[i];
        }

        //----menghapus tanda baca----
        //----menghapus tanda baca----
        String copydoc3[] = doc3.split("");
        for (int i = 0; i < copydoc3.length; i++) {
            for (int j = 0; j < lexical.length; j++) {
                if (copydoc3[i].equalsIgnoreCase(lexical[j])) {
                    copydoc3[i] = "";
                }
            }
        }
        doc3 = "";
        for (int i = 0; i < copydoc3.length; i++) {
            doc3 = doc3 + copydoc3[i];
        }

        //----menghapus tanda baca----
        //----menghapus tanda baca----
        String copydoc4[] = doc4.split("");
        for (int i = 0; i < copydoc4.length; i++) {
            for (int j = 0; j < lexical.length; j++) {
                if (copydoc4[i].equalsIgnoreCase(lexical[j])) {
                    copydoc4[i] = "";
                }
            }
        }
        doc4 = "";
        for (int i = 0; i < copydoc4.length; i++) {
            doc4 = doc4 + copydoc4[i];
        }

        //----menghapus tanda baca----
        //----menghapus tanda baca----
        String copydoc5[] = doc5.split("");
        for (int i = 0; i < copydoc5.length; i++) {
            for (int j = 0; j < lexical.length; j++) {
                if (copydoc5[i].equalsIgnoreCase(lexical[j])) {
                    copydoc5[i] = "";
                }
            }
        }
        doc5 = "";
        for (int i = 0; i < copydoc5.length; i++) {
            doc5 = doc5 + copydoc5[i];
        }

        //----menghapus tanda baca----
        //doc1 = doc1.replaceAll(",", "");
        String[] words1 = doc1.split(" ");
        for (String word1 : words1) {
            wordsList1.add(word1);
        }
        System.out.println("Token :  \n" + wordsList1);

        String[] words2 = doc2.split(" ");
        for (String word2 : words2) {
            wordsList2.add(word2);
        }
        System.out.println("Token :  \n" + wordsList2);

        String[] words3 = doc3.split(" ");
        for (String word3 : words3) {
            wordsList3.add(word3);
        }
        System.out.println("Token :  \n" + wordsList3);

        String[] words4 = doc4.split(" ");
        for (String word4 : words4) {
            wordsList4.add(word4);
        }
        System.out.println("Token :  \n" + wordsList4);

        String[] words5 = doc5.split(" ");
        for (String word5 : words5) {
            wordsList5.add(word5);
        }
        System.out.println("Token :  \n" + wordsList5);

        jTextArea3.setText("Doc 1 : " + wordsList1 + "\n\n"
                + "Doc 2 : " + wordsList2 + "\n\n"
                + "Doc 3 : " + wordsList3 + "\n\n"
                + "Doc 4 : " + wordsList4 + "\n\n"
                + "Doc 5 : " + wordsList5 + "\n\n");

//        for (int i = 0; i < stopWords.length; i++) {
//            if (tokenisasi.contains(stopWords[i])) {
//                //menghapus spasi di akhir
//                tokenisasi = tokenisasi.replaceAll(stopWords[i] + "\\s+", " ");
//            }
//        }
        jButton1.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
        //   try {
        //menghapus kata-kata yang ada di daftar

        int banyak1 = wordsList1.size();
        int banyak2 = wordsList2.size();
        int banyak3 = wordsList3.size();
        int banyak4 = wordsList4.size();
        int banyak5 = wordsList5.size();

        String w1[] = new String[banyak1];
        String w2[] = new String[banyak2];
        String w3[] = new String[banyak3];
        String w4[] = new String[banyak4];
        String w5[] = new String[banyak5];
        for (int i = 0; i < wordsList1.size(); i++) {
            w1[i] = wordsList1.get(i);
        }

        for (int i = 0; i < wordsList2.size(); i++) {
            w2[i] = wordsList2.get(i);
        }
        for (int i = 0; i < wordsList3.size(); i++) {
            w3[i] = wordsList3.get(i);
        }
        for (int i = 0; i < wordsList4.size(); i++) {
            w4[i] = wordsList4.get(i);
        }
        for (int i = 0; i < wordsList5.size(); i++) {
            w5[i] = wordsList5.get(i);
        }
        ArrayList<String> hapus1 = new ArrayList<String>();
        ArrayList<String> hapus2 = new ArrayList<String>();
        ArrayList<String> hapus3 = new ArrayList<String>();
        ArrayList<String> hapus4 = new ArrayList<String>();
        ArrayList<String> hapus5 = new ArrayList<String>();
        int triger1 = 0;
        int triger2 = 0;
        int triger3 = 0;
        int triger4 = 0;
        int triger5 = 0;

        for (int i = 0; i < banyak1; i++) {
            for (int j = 0; j < stopWords.length; j++) {
                if (stopWords[j].equalsIgnoreCase(w1[i])) {
                    System.out.println("doc 1 yang dihapus " + stopWords[j]);
                    hapus1.add(triger1, stopWords[j]);
                    break;
                }

            }
        }

        for (int i = 0; i < banyak2; i++) {
            for (int j = 0; j < stopWords.length; j++) {
                if (stopWords[j].equalsIgnoreCase(w2[i])) {
                    System.out.println("doc 2 yang dihapus " + stopWords[j]);
                    hapus2.add(triger2, stopWords[j]);
                    break;
                }

            }
        }
        for (int i = 0; i < banyak3; i++) {
            for (int j = 0; j < stopWords.length; j++) {
                if (stopWords[j].equalsIgnoreCase(w3[i])) {
                    System.out.println("doc 3 yang dihapus " + stopWords[j]);
                    hapus3.add(triger3, stopWords[j]);
                    break;
                }

            }
        }

        for (int i = 0; i < banyak4; i++) {
            for (int j = 0; j < stopWords.length; j++) {
                if (stopWords[j].equalsIgnoreCase(w4[i])) {
                    System.out.println("doc 4 yang dihapus " + stopWords[j]);
                    hapus4.add(triger4, stopWords[j]);
                    break;
                }

            }
        }

        for (int i = 0; i < banyak5; i++) {
            for (int j = 0; j < stopWords.length; j++) {
                if (stopWords[j].equalsIgnoreCase(w5[i])) {
                    System.out.println("doc 5 yang dihapus " + stopWords[j]);
                    hapus5.add(triger5, stopWords[j]);
                    break;
                }

            }
        }
        //hapus
        for (int i = 0; i < hapus1.size(); i++) {
            wordsList1.remove(hapus1.get(i));
        }
        //hapus
        for (int i = 0; i < hapus2.size(); i++) {
            wordsList2.remove(hapus2.get(i));
        }
        //hapus
        for (int i = 0; i < hapus3.size(); i++) {
            wordsList3.remove(hapus3.get(i));
        }
        //hapus
        for (int i = 0; i < hapus4.size(); i++) {
            wordsList4.remove(hapus4.get(i));
        }
        //hapus
        for (int i = 0; i < hapus5.size(); i++) {
            wordsList5.remove(hapus5.get(i));
        }

        jTextArea4.setText("Doc 1 : " + wordsList1 + "\n\n"
                + "Doc 2 : " + wordsList2 + "\n\n"
                + "Doc 3 : " + wordsList3 + "\n\n"
                + "Doc 4 : " + wordsList4 + "\n\n"
                + "Doc 5 : " + wordsList5 + "\n\n");
        jButton3.setEnabled(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
        IndonesianStemmer stemmer = new IndonesianStemmer();
        // Stemming Document 1
        for (int i = 0; i < wordsList1.size(); i++) {
            dooc1.add(wordsList1.get(i));
            char[] chars = dooc1.get(i).toCharArray();
            int len = stemmer.stem(chars, chars.length, true);
            String stem = new String(chars, 0, len);
            repdoc1.add(stem);
        }
        // Stemming Document 2
        for (int i = 0; i < wordsList2.size(); i++) {
            dooc2.add(wordsList2.get(i));
            char[] chars = dooc2.get(i).toCharArray();
            int len = stemmer.stem(chars, chars.length, true);
            String stem = new String(chars, 0, len);
            repdoc2.add(stem);
        }
        // Stemming Document 3
        for (int i = 0; i < wordsList3.size(); i++) {
            dooc3.add(wordsList3.get(i));
            char[] chars = dooc3.get(i).toCharArray();
            int len = stemmer.stem(chars, chars.length, true);
            String stem = new String(chars, 0, len);
            repdoc3.add(stem);
        }
        // Stemming Document 4
        for (int i = 0; i < wordsList4.size(); i++) {
            dooc4.add(wordsList4.get(i));
            char[] chars = dooc4.get(i).toCharArray();
            int len = stemmer.stem(chars, chars.length, true);
            String stem = new String(chars, 0, len);
            repdoc4.add(stem);
        }
        // Stemming Document 5 
        for (int i = 0; i < wordsList5.size(); i++) {
            dooc5.add(wordsList5.get(i));
            char[] chars = dooc5.get(i).toCharArray();
            int len = stemmer.stem(chars, chars.length, true);
            String stem = new String(chars, 0, len);
            repdoc5.add(stem);
        }

//       
        jTextArea5.setText("Doc 1 : " + repdoc1 + "\n\n"
                + "Doc 2 : " + repdoc2 + "\n\n"
                + "Doc 3 : " + repdoc3 + "\n\n"
                + "Doc 4 : " + repdoc4 + "\n\n"
                + "Doc 5 : " + repdoc5 + "\n\n");

        for (int i = wordsList1.size() - 1; i >= 0; i--) {
            wordsList1.remove(i);
        }
        for (int i = 0; i < repdoc1.size(); i++) {
            wordsList1.add(i, repdoc1.get(i));
        }

        for (int i = wordsList2.size() - 1; i >= 0; i--) {
            wordsList2.remove(i);
        }
        for (int i = 0; i < repdoc2.size(); i++) {
            wordsList2.add(i, repdoc2.get(i));
        }

        for (int i = wordsList3.size() - 1; i >= 0; i--) {
            wordsList3.remove(i);
        }
        for (int i = 0; i < repdoc3.size(); i++) {
            wordsList3.add(i, repdoc3.get(i));
        }

        for (int i = wordsList4.size() - 1; i >= 0; i--) {
            wordsList4.remove(i);
        }
        for (int i = 0; i < repdoc4.size(); i++) {
            wordsList4.add(i, repdoc4.get(i));
        }

        for (int i = wordsList5.size() - 1; i >= 0; i--) {
            wordsList5.remove(i);
        }
        for (int i = 0; i < repdoc5.size(); i++) {
            wordsList5.add(i, repdoc5.get(i));
        }

        jButton2.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
        doc1 = "";
        doc2 = "";
        doc3 = "";
        doc4 = "";
        doc5 = "";
        for (int i = 0; i < wordsList1.size(); i++) {
            doc1 = doc1 + wordsList1.get(i) + " ";
        }
        doc1 = doc1.trim();

        for (int i = 0; i < wordsList2.size(); i++) {
            doc2 = doc2 + wordsList2.get(i) + " ";
        }
        doc2 = doc2.trim();

        for (int i = 0; i < wordsList3.size(); i++) {
            doc3 = doc3 + wordsList3.get(i) + " ";
        }
        doc3 = doc3.trim();

        for (int i = 0; i < wordsList4.size(); i++) {
            doc4 = doc4 + wordsList4.get(i) + " ";
        }
        doc4 = doc4.trim();

        for (int i = 0; i < wordsList5.size(); i++) {
            doc5 = doc5 + wordsList5.get(i) + " ";
        }
        doc5 = doc5.trim();

        String term = doc1 + " " + doc2 + " " + doc3 + " " + doc4 + " " + doc5;
        System.out.println("ini term " + term);

        int plus = 0;
        String proseskatamasukan = " ";

        boolean perhitungan = false;
        String[] splitword = term.split("\\s|\\.");

        for (int i = 0; i < splitword.length; i++) {
            String word = splitword[i];

            perhitungan = false;

            if (!proseskatamasukan.contains(word)) {
                for (int j = 0; j < splitword.length; j++) {
                    if (word.equals(splitword[j])) {
                        perhitungan = true;
                        proseskatamasukan = proseskatamasukan + word + " ";
                    }
                }
            }
            if (perhitungan) {
                hasilterm.add(word);
            }
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, hasilterm.size());
        jTable1.setModel(tableModel);

//        for (int i = 0; i < banyaklist; i++) {
//            jTable1.setValueAt(hasilterm.get(i), i, 0);
//
//        }
        for (int j = 0; j < hasilterm.size(); j++) {
            System.out.println(hasilterm.get(j));
            jTable1.setValueAt(hasilterm.get(j), j, 0);

        }

        //hilangkan spasi di term 
        int jumlahterm1 = 0;
        int jumlahterm2 = 0;
        int jumlahterm3 = 0;
        int jumlahterm4 = 0;
        int jumlahterm5 = 0;
        for (int i = 0; i < hasilterm.size(); i++) {
            for (int j = 0; j < wordsList1.size(); j++) {
                if (wordsList1.get(j).equalsIgnoreCase(String.valueOf(hasilterm.get(i)))) {
                    jumlahterm1++;
                }
            }
            jTable1.setValueAt(jumlahterm1, i, 1);
            jumlahterm1 = 0;
        }

        for (int i = 0; i < hasilterm.size(); i++) {
            for (int j = 0; j < wordsList2.size(); j++) {
                if (wordsList2.get(j).equalsIgnoreCase(String.valueOf(hasilterm.get(i)))) {
                    jumlahterm2++;
                }
            }
            jTable1.setValueAt(jumlahterm2, i, 2);
            jumlahterm2 = 0;
        }
        for (int i = 0; i < hasilterm.size(); i++) {
            for (int j = 0; j < wordsList3.size(); j++) {
                if (wordsList3.get(j).equalsIgnoreCase(String.valueOf(hasilterm.get(i)))) {
                    jumlahterm3++;
                }
            }
            jTable1.setValueAt(jumlahterm3, i, 3);
            jumlahterm3 = 0;
        }
        for (int i = 0; i < hasilterm.size(); i++) {
            for (int j = 0; j < wordsList4.size(); j++) {
                if (wordsList4.get(j).equalsIgnoreCase(String.valueOf(hasilterm.get(i)))) {
                    jumlahterm4++;
                }
            }
            jTable1.setValueAt(jumlahterm4, i, 4);
            jumlahterm4 = 0;
        }
        for (int i = 0; i < hasilterm.size(); i++) {
            for (int j = 0; j < wordsList5.size(); j++) {
                if (wordsList5.get(j).equalsIgnoreCase(String.valueOf(hasilterm.get(i)))) {
                    jumlahterm5++;
                }
            }
            jTable1.setValueAt(jumlahterm5, i, 5);
            jumlahterm5 = 0;
        }
        jButton4.setEnabled(false);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        double hasiltf = 0;
//        hasiltf = 1 + Math.log10(Double.valueOf(String.valueOf(jTable1.getValueAt(0, 1))));
//        System.out.println("nilai 0,1 "+Double.valueOf(String.valueOf(jTable1.getValueAt(0, 1))));
//        System.out.println("table 0,1" +hasiltf);
        for (int j = 1; j < 6; j++) {
            for (int i = 0; i < hasilterm.size(); i++) {
                if (Double.valueOf(String.valueOf(jTable1.getValueAt(i, j))) == 0) {
                    jTable1.setValueAt(0, i, j);
                } else {
                    hasiltf = 1 + Math.log10(Double.valueOf(String.valueOf(jTable1.getValueAt(i, j))));
                    jTable1.setValueAt(hasiltf, i, j);
                    hasiltf = 0;
                }
            }
        }
        jButton5.setEnabled(false);
        pemicu2 = 1;

        if (pemicu2 == 1 && pemicu == 2) {
            jButton7.setEnabled(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (pemicu == 0) {
            int df = 0;
            for (int j = 0; j < hasilterm.size(); j++) {
                for (int i = 1; i < 6; i++) {
                    if (Double.valueOf(String.valueOf(jTable1.getValueAt(j, i))) != 0) {
                        df++;
                    }
                }
                jTable1.setValueAt(df, j, 6);
                df = 0;
            }

            jButton6.setText("Hitung IDF");
            pemicu = 1;
        } else {

            for (int i = 0; i < hasilterm.size(); i++) {
                if (Double.valueOf(String.valueOf(jTable1.getValueAt(i, 6))) == 0) {
                    jTable1.setValueAt(0, i, 6);
                } else {
                    jTable1.setValueAt(Math.log10(5 / Double.valueOf(String.valueOf(jTable1.getValueAt(i, 6)))), i, 6);
                }
            }
            jButton6.setEnabled(false);
            pemicu = 2;
        }

        if (pemicu2 == 1 && pemicu == 2) {
            jButton7.setEnabled(true);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        double hasiltfidf = 0;
        for (int j = 1; j < 6; j++) {
            for (int i = 0; i < hasilterm.size(); i++) {
                hasiltfidf = Double.valueOf(String.valueOf(jTable1.getValueAt(i, j))) * Double.valueOf(String.valueOf(jTable1.getValueAt(i, 6)));
                jTable1.setValueAt(hasiltfidf, i, j);
                hasiltfidf = 0;

            }

        }

        jButton7.setEnabled(false);
        jLabel9.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Irwin Deriyan Ferdiansyah                145150201111007\n"
                                          + "Muhammad Bima Zehansyah         145150207111011\n"
                                          + "Novia Agusvina                                   145150201111108\n"
                                          + "Alifia Nurlita                                         145150200111127\n", "Anggota Kelompok", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tugasakhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tugasakhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tugasakhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tugasakhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tugasakhir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jdoc1;
    private javax.swing.JTextArea jdoc2;
    private javax.swing.JTextArea jdoc3;
    private javax.swing.JTextArea jdoc4;
    private javax.swing.JTextArea jdoc5;
    // End of variables declaration//GEN-END:variables
}
