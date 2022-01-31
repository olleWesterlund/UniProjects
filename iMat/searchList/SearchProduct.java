package iMat.searchList;

import iMat.Model;
import se.chalmers.cse.dat216.project.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchProduct {
    private Map<String, Product> productMap = new HashMap<>();
    private String ekologisk       = "" + ";" + c.ekologisk + ";" + c.ekologiska + ";" + c.ekologiskt;
    private String kål             = "" + ";" + c.grönsaker + ";" + c.grönt + ";" + c.kål;
    private String sallad          = "" + ";" + c.grönsaker + ";" + c.grönt + ";" + c.sallad;
    private String frukt           = "" + ";" + c.frukt + ";" + c.frukter + ";" + c.grönsaker + ";" + c.grönt;
    private String kärnfrukt       = frukt + ";" + c.kärnfrukt;
    private String melon           = "" + ";" + c.melon + ";" + c.frukt + ";" + c.grönt;
    private String exotisk_frukt   = frukt + ";" + c.exotisk_frukt;
    private String mjöl            = "" + ";" + c.mjöl + ";" + c.bakingrediens + ";" + c.basvaror;
    private String socker          = "" + ";" + c.socker + ";" + c.bakingrediens + ";" + c.basvaror;
    private String ost             = "" + ";" + c.ost + ";" + c.efterrätt + ";" + c.mejeriprodukter;
    private String hårdost         = ost + ";" + c.hårdost;
    private String mjukost         = ost + ";" + c.mjukost;
    private String färskost        = ost + ";" + c.färskost;
    private String vitost          = ost + ";" + c.vitost;
    private String lagrad_ost      = hårdost + ";" + c.lagrad_ost;
    private String nötkött         = "" + ";" + c.kött + ";" + c.kokött + ";" + c.nötkött;
    private String grönsak         = "" + ";" + c.grönsaker + ";" + c.grönt;
    private String kyckling        = "" + ";" + c.kyckling + ";" + c.kött + ";" + c.fågel;
    private String kycklingEko     = ekologisk + ";" + kyckling;
    private String nötter          = "" + ";" + c.nötter;
    private String frö             = "" + ";" + c.frö + ";" + c.frön + ";" + c.kärnor;
    private String pasta           = "" + ";" + c.pasta + ";" + c.basvaror;
    private String fylldpasta      = pasta + ";" + c.fylldpasta;
    private String spaghetti       = pasta + ";" + c.spaghetti + ";" + c.spagetti;
    private String mackaroner      = pasta + ";" + c.ihålig + ";" + c.mackaroner;
    private String bandpasta       = pasta + spaghetti + ";" + c.bandpasta;
    private String lasagne         = pasta + ";" + c.platt + ";" + c.lasagne;
    private String ris             = "" + ";" + c.ris + ";" + c.basvaror;
    private String potatis         = "" + ";" + c.potatis + ";" + c.basvaror;
    private String potatispure     = potatis + ";" + c.potatispure;
    private String rotsak          = grönsak + ";" + c.rotsaker;
    private String godis           = "" + ";" + c.godis + ";" + c.snacks + ";" + c.fredagsmys + ";" + c.lördagsgodis;
    private String chips           = godis + ";" + c.chips;
    private String chokladkaka     = godis + ";" + c.chokladkaka;
    private String glass           = godis + ";" + c.glass + ";" + c.efterrätt;
    private String fikabröd        = "" + ";" + c.fikabröd + ";" + c.efterrätt + ";" + c.fika;
    private String bulle           = fikabröd + ";" + c.bulle + ";" + c.bullar;
    private String kaka            = fikabröd + ";" + c.kaka + ";" + c.kakor;
    private String kex             = fikabröd + ";" + c.kex;
    private String koditorivara    = fikabröd + ";" + c.koditorivara + ";" + c.bakelse;
    private String praliner        = godis + ";" + c.praliner + ";" + c.lördagsgodis;
    private String krydda          = "" + ";" + c.krydda;
    private String grön_krydda     = krydda + ";" + c.grön_krydda;
    private String mjölkprodukter  = "" + ";" + c.mjölkprodukter + ";" + c.mejeriprodukter;
    private String fil             = mjölkprodukter + ";" + c.fil + ";" + c.syrade;
    private String mjölk           = mjölkprodukter + ";" + c.mjölk;
    private String yoghurt         = mjölkprodukter + ";" + c.yoghurt + ";" + c.syrade;
    private String baljväxter      = "" + ";" + c.baljväxter;
    private String ärtor           = baljväxter + ";" + c.ärtor;
    private String linser          = baljväxter + ";" + c.linser;
    private String bönor           = baljväxter + ";" + c.bönor;
    private String matbröd         = "" + ";" + c.matbröd+ ";" + c.bröd;
    private String mjuktbröd       = matbröd + ";" + c.mjukt_bröd;
    private String hårtbröd        = matbröd + ";" + c.hårt_bröd;
    private String rostbröd        = mjuktbröd + ";" + c.rostbröd;
    private String knäckebröd      = matbröd + ";" + c.knäckebröd;
    private String tunnbröd        = matbröd + ";" + c.tunnbröd;
    private String julbröd         = mjuktbröd + ";" + c.julbröd;
    private String bär             = "" + ";" + c.bär;
    private String hallonsläktet   = bär + ";" + c.hallonsläktet;
    private String vinbär          = bär + ";" + c.vinbär;
    private String citrusfrukter   = frukt + ";" + c.citrusfrukter;
    private String salt            = ";" + c.salt + ";" + c.basvaror;
    private String ägg             = ";" + c.mejeriprodukter + ";" + c.ägg + ";" + c.hönsägg;
    private String cacao           = ";" + c.cacao ;
    private String varm_dryck      = ";" + c.varm_dryck + ";" + c.varma_drycker;
    private String kall_dryck      = ";" + c.kall_dryck + ";" + c.kalla_drycker;
    private String te              = varm_dryck + ";" + c.te + ";" + c.the;
    private String svart_te        = te + ";" + c.svart_te ;
    private String grönt_te        = te + ";" + c.grönt_te ;
    private String kaffe           = varm_dryck + ";" + c.kaffe ;
    private String rött_te         = te + ";" + c.rött_te;
    private String juice           = kall_dryck + ";" + c.juice + ";" + c.jos ;
    private String läsk            = kall_dryck + ";" + c.läsk + ";" + c.kolsyrad_dryck + ";" + c.kolsyrat + ";" + c.dricka;
    private String soppa           = kall_dryck + ";" + c.soppa ;
    private String vatten          = kall_dryck + ";" + c.vatten ;
    private String fisk            = ";" + c.fisk;
    private String kräftdjur       = fisk + ";" + c.kräftdjur + ";" + c.skaldjur;
    private String grönsaker       = ";" + c.grönsaker;
    private String färs            = ";" + c.färs;
    private String nötfärs         = färs + ";" + c.nötfärs + ";" + c.köttfärs + ";" + nötkött;
    private String nötfläskkötteko = nötfärs + ";" + ekologisk + ";" + c.fläskkött + ";" + c.griskött + ";" + c.nötfläskkötteko;
    private String nötfärseko      = nötfärs + ";" + c.nötkötteko + ekologisk;
    private String sockereko       = socker + ";" + c.sockereko   + ekologisk;
    private String kåleko          = kål + ";" + c.kåleko         + ekologisk;
    private String äggeko          = ägg + ";" + c.äggeko         + ekologisk;

    public SearchProduct(){
        initSearchIndex();
    }
    private void generateSearchText(Integer idNbr, String searchText){
        Product product = Model.getInstance().getProduct(idNbr);

        if (product.isEcological()){
            searchText += ";" + ekologisk;
        }
        String genSearchText = ";" + product.getName() + ";" +
//                               product.getCategory().name() + ";" +
                               searchText;
        productMap.put(genSearchText.toLowerCase(), product);
    }
    public List<Product> find(String searchText){
        List<Product> products = new ArrayList<>();

        String searchRegEx = ".*(\\Q" + ";" + searchText.toLowerCase() + "\\E|\\Q" + " " + searchText.toLowerCase() + "\\E).*";
        for(String key : productMap.keySet()) {
            String productSearchText = key.replace("_", " ");
            if(productSearchText.matches(searchRegEx)) {
                products.add(productMap.get(key));
            }
        }
        return products;
    }
    private enum c{
        melon,           frukt,           exotisk_frukt,   kärnfrukt,         grönt,         kött,
        ost,             efterrätt,       bröd,            grönsak,           rotfrukt,      mjöl,
        socker,          sallad,          kål,             bakingrediens,     nötkött,       fläskkött,
        kyckling,        fågel,           fisk,            ekologisk,         ekologiska,    ekologiskt,
        nötter,          frö,             pasta,           fylldpasta,        spagetti,      spaghetti,
        mackaroner,      ihålig,          bandpasta,       platt,             lasagne,       ris,
        potatis,         basvaror,        potatispure,     rotsaker,          godis,         fredagsmys,
        lördagsgodis,    chips,           snacks,          chokladkaka,       glass,         fikabröd,
        fika,            bulle,           kaka,            kex,               koditorivara,  bakelse,
        praliner,        krydda,          grön_krydda,     mjölkprodukter,    fil,           yoghurt,
        mjölk,           mejeriprodukter, syrade,          ärtor,             baljväxter,    linser,
        bönor,           matbröd,         rostbröd,        knäckebröd,        tunnbröd,      julbröd,
        hallonsläktet,   vinbär,          citrusfrukter,   bär,               mjukt_bröd,    hårt_bröd,
        salt,            färskost,        mjukost,         hårdost,           vitost,        lagrad_ost,
        ägg,             cacao,           te,              the,               svart_te,      grönt_te,
        kaffe,           rött_te,         juice,           läsk,              soppa,         vatten,
        varm_dryck,      varma_drycker,   kall_dryck,      kalla_drycker,     kräftdjur,     grönsaker,
        nötfläskkötteko, nötkötteko,      sockereko,       kåleko,            äggeko,        bullar,
        kakor,           jos,             kolsyrad_dryck,  kolsyrat,          frukter,       kokött,
        griskött,        färs,            nötfärs,         köttfärs,          hönsägg,       skaldjur,
        dricka,          frön,            kärnor
    }

    private void initSearchIndex(){
        generateSearchText(87 , melon              ); // MELONS;Galiamelon;29.95;kr/kg;product_87.jpg;end
        generateSearchText(92 , mjöl               ); // FLOUR_SUGAR_SALT;Gramsmj�l;12.75;kr/f�rp;product_92.jpg;end
        generateSearchText(95 , socker             ); // FLOUR_SUGAR_SALT;Socker;15.95;kr/f�rp;product_95.jpg;end
        generateSearchText(71 , nötkött            ); // MEAT;Grytbitar n�t;89.0;kr/kg;product_71.jpg;end
        generateSearchText(77 , ost                ); // DAIRIES;Brie;15.0;kr/st;product_77.jpg;end
        generateSearchText(62 , frukt              ); // VEGETABLE_FRUIT;Squash;59.0;kr/kg;product_62.jpg;end
        generateSearchText(63 , kål                ); // CABBAGE;Blomk�l;39.95;kr/kg;product_63.jpg;end
        generateSearchText(64 , grönsak            ); // CABBAGE;Broccoli;39.8;kr/kg;product_64.jpg;end
        generateSearchText(65 , kål                ); // CABBAGE;Brysselk�l;14.95;kr/kg;product_65.jpg;end
        generateSearchText(66 , kål                ); // CABBAGE;Kinak�l;14.95;kr/kg;product_66.jpg;end
        generateSearchText(67 , sallad             ); // CABBAGE;Romanesco;37.9;kr/kg;product_67.jpg;end
        generateSearchText(68 , kål                ); // CABBAGE;R�dk�l;29.95;kr/kg;product_68.jpg;end
        generateSearchText(69 , kåleko             ); // CABBAGE;Ekologisk vitk�l;5.95;kr/kg;product_69.jpg;end
        generateSearchText(70 , kål                ); // CABBAGE;Savoyk�l;25.9;kr/kg;product_70.jpg;end
        generateSearchText(72 , nötkött            ); // MEAT;H�grev;69.0;kr/kg;product_72.jpg;end
        generateSearchText(73 , kyckling           ); // MEAT;Kycklingbr�stfil�;139.0;kr/kg;product_73.jpg;end
        generateSearchText(74 , kyckling           ); // MEAT;Kycklingklubbor;31.0;kr/kg;product_74.jpg;end
        generateSearchText(75 , kycklingEko        ); // MEAT;Ekologisk kyckling hel;59.0;kr/kg;product_75.jpg;end
        generateSearchText(76 , nötfärs            ); // MEAT;K�ttf�rs n�t;50.95;kr/kg;product_76.jpg;end
        generateSearchText(97 , nötter             ); // NUTS_AND_SEEDS;Cashewn�t;39.95;kr/kg;product_97.jpg;end
        generateSearchText(98 , nötter             ); // NUTS_AND_SEEDS;Hasseln�t;80.0;kr/kg;product_98.jpg;end
        generateSearchText(99 , nötter             ); // NUTS_AND_SEEDS;Jordn�t;79.0;kr/kg;product_99.jpg;end
        generateSearchText(100, nötter             ); // NUTS_AND_SEEDS;Kokosn�t;14.0;kr/kg;product_100.jpg;end
        generateSearchText(101, nötter             ); // NUTS_AND_SEEDS;Mandel;53.0;kr/kg;product_101.jpg;end
        generateSearchText(102, nötter             ); // NUTS_AND_SEEDS;Pistagen�t;72.0;kr/kg;product_102.jpg;end
        generateSearchText(103, nötter             ); // NUTS_AND_SEEDS;Valn�t;69.0;kr/kg;product_103.jpg;end
        generateSearchText(104, frö                ); // NUTS_AND_SEEDS;Pumpak�rnor;195.0;kr/kg;product_104.jpg;end
        generateSearchText(105, frö                ); // NUTS_AND_SEEDS;Solrosfr�n;150.0;kr/kg;product_105.jpg;end
        generateSearchText(106, pasta              ); // PASTA;Farfalle;14.0;kr/f�rp;product_106.jpg;end
        generateSearchText(107, bandpasta          ); // PASTA;Fettuccine;16.0;kr/f�rp;product_107.jpg;end
        generateSearchText(108, mackaroner         ); // PASTA;Fusilli;18.0;kr/f�rp;product_108.jpg;end
        generateSearchText(109, lasagne            ); // PASTA;Lasagne;16.0;kr/f�rp;product_109.jpg;end
        generateSearchText(110, mackaroner         ); // PASTA;Penne;9.9;kr/f�rp;product_110.jpg;end
        generateSearchText(111, mackaroner         ); // ;PASTA;Rigatoni;11.0;kr/f�rp;product_111.jpg;end
        generateSearchText(112, spaghetti          ); // ;PASTA;Spaghetti;12.0;kr/f�rp;product_112.jpg;end
        generateSearchText(113, ris                ); // ;POTATO_RICE;Basmati-ris;24.0;kr/kg;product_113.jpg;end
        generateSearchText(114, ris                ); // ;POTATO_RICE;Fullkornsris;28.0;kr/kg;product_114.jpg;end
        generateSearchText(115, ris                ); // ;POTATO_RICE;Jasmin-ris;25.0;kr/kg;product_115.jpg;end
        generateSearchText(116, potatis            ); // ;POTATO_RICE;Potatis;7.0;kr/kg;product_116.jpg;end
        generateSearchText(117, potatis            ); // ;POTATO_RICE;Potatis r�d;6.0;kr/kg;product_117.jpg;end
        generateSearchText(118, potatis            ); // ;POTATO_RICE;Potatis s�t;26.0;kr/kg;product_118.jpg;end
        generateSearchText(119, potatispure        ); // ;POTATO_RICE;Potatispur�;9.9;kr/f�rp;product_119.jpg;end
        generateSearchText(120, ris                ); // ;POTATO_RICE;R�ris;24.0;kr/kg;product_120.jpg;end
        generateSearchText(121, rotsak             ); // ;ROOT_VEGETABLE;K�lrot;9.95;kr/kg;product_121.jpg;end
        generateSearchText(122, rotsak             ); // ;ROOT_VEGETABLE;Majrova;10.0;kr/kg;product_122.jpg;end
        generateSearchText(123, rotsak             ); // ;ROOT_VEGETABLE;Morot;5.0;kr/kg;product_123.jpg;end
        generateSearchText(124, rotsak             ); // ;ROOT_VEGETABLE;Palsternacka;20.0;kr/kg;product_124.jpg;end
        generateSearchText(125, rotsak             ); // ;ROOT_VEGETABLE;Pepparrot;119.0;kr/kg;product_125.jpg;end
        generateSearchText(126, rotsak             ); // ;ROOT_VEGETABLE;R�disa;86.0;kr/kg;product_126.jpg;end
        generateSearchText(127, rotsak             ); // ;ROOT_VEGETABLE;R�dbeta;4.9;kr/kg;product_127.jpg;end
        generateSearchText(128, rotsak             ); // ;ROOT_VEGETABLE;Rotselleri;12.0;kr/kg;product_128.jpg;end
        generateSearchText(129, kärnfrukt          ); // ;FRUIT;Aprikos;40.0;kr/kg;product_129.jpg;end
        generateSearchText(130, kärnfrukt          ); // ;FRUIT;K�rsb�r;59.0;Kr/kg;product_130.jpg;end
        generateSearchText(131, kärnfrukt          ); // ;FRUIT;Nektarin;45.0;kr/kg;product_131.jpg;end
        generateSearchText(132, kärnfrukt          ); // ;FRUIT;Persika;39.0;kr/kg;product_132.jpg;end
        generateSearchText(133, kärnfrukt          ); // ;FRUIT;Plommon;38.0;kr/kg;product_133.jpg;end
        generateSearchText(134, chips              ); // ;SWEET;Chips;17.0;kr/p�se;product_134.gif;end
        generateSearchText(135, chokladkaka        ); // ;SWEET;Choklad;19.0;kr/st;product_135.jpg;end
        generateSearchText(136, glass              ); // ;SWEET;Glass;29.0;kr/l;product_136.jpg;end
        generateSearchText(137, bulle              ); // ;SWEET;Kanelbullar;10.0;kr/st;product_137.jpg;end
        generateSearchText(138, kex                ); // ;SWEET;Kex;13.0;kr/f�rp;product_138.jpg;end
        generateSearchText(139, koditorivara       ); // ;SWEET;Kokosbollar;3.0;kr/st;product_139.jpg;end
        generateSearchText(140, praliner           ); // ;SWEET;Praliner;52.0;kr/f�rp;product_140.jpg;end
        generateSearchText(141, grön_krydda        ); // ;HERB;Basilika;16.0;kr/st;product_141.jpg;end
        generateSearchText(142, krydda             ); // ;HERB;Citronmeliss;15.0;kr/st;product_142.jpg;end
        generateSearchText(143, krydda             ); // ;HERB;Dill;13.0;kr/st;product_143.jpg;end
        generateSearchText(144, krydda             ); // ;HERB;Koriander;16.0;kr/st;product_144.jpg;end
        generateSearchText(145, grön_krydda        ); // ;HERB;Oregano;16.0;kr/st;product_145.jpg;end
        generateSearchText(146, krydda             ); // ;HERB;Persilja;12.0;kr/st;product_146.jpg;end
        generateSearchText(147, grön_krydda        ); // ;HERB;Rosmarin;13.0;kr/st;product_147.jpg;end
        generateSearchText(148, grön_krydda        ); // ;HERB;Timjan;15.0;kr/st;product_148.jpg;end
        generateSearchText(78 , fil                ); // ;DAIRIES;Filmj�lk;12.0;kr/st;product_78.jpg;end
        generateSearchText(79 , färskost           ); // ;DAIRIES;F�rskost;14.0;kr/st;product_79.jpg;end
        generateSearchText(80 , mjölk              ); // ;DAIRIES;Mj�lk;10.0;kr/st;product_80.jpg;end
        generateSearchText(81 , vitost             ); // ;DAIRIES;Mozzarella;16.0;kr/st;product_81.jpg;end
        generateSearchText(82 , lagrad_ost         ); // ;DAIRIES;V�sterbotten;50.0;kr/st;product_82.jpg;end
        generateSearchText(83 , yoghurt            ); // ;DAIRIES;Yoghurt laktosfri;14.0;kr/st;product_83.jpg;end
        generateSearchText(84 , yoghurt            ); // ;DAIRIES;Yoghurt turkisk;18.0;kr/st;product_84.jpg;end
        generateSearchText(85 , ägg                ); // ;DAIRIES;�gg;12.5;kr/f�rp;product_85.jpg;end
        generateSearchText(86 , melon              ); // ;MELONS;Charentaismelon;25.0;kr/kg;product_86.jpg;end
        generateSearchText(88 , melon              ); // ;MELONS;Honungsmelon;19.9;kr/kg;product_88.jpg;end
        generateSearchText(89 , melon              ); // ;MELONS;N�tmelon;29.95;kr/kg;product_89.jpg;end
        generateSearchText(90 , melon              ); // ;MELONS;Vattenmelon;9.95;kr/kg;product_90.jpg;end
        generateSearchText(91 , socker             ); // ;FLOUR_SUGAR_SALT;Farinsocker;19.0;kr/f�rp;product_91.jpg;end
        generateSearchText(93 , mjöl               ); // ;FLOUR_SUGAR_SALT;R�gmj�l;12.7;kr/f�rp;product_93.jpg;end
        generateSearchText(94 , salt               ); // ;FLOUR_SUGAR_SALT;Salt;5.4;kr/f�rp;product_94.jpg;end
        generateSearchText(96 , mjöl               ); // ;FLOUR_SUGAR_SALT;Vetemj�l;18.0;kr/f�rp;product_96.jpg;end
        generateSearchText(1  , ärtor              ); // ;POD;Gr�na �rter;19.5;kr/kg;product_1.jpg;end
        generateSearchText(2  , ärtor              ); // ;POD;Kik�rtor;24.0;kr/kg;product_2.jpg;end
        generateSearchText(3  , linser             ); // ;POD;Linser bruna;18.0;kr/f�rp;product_3.jpg;end
        generateSearchText(4  , linser             ); // ;POD;Linser gr�na;12.0;kr/f�rp;product_4.jpg;end
        generateSearchText(5  , linser             ); // ;POD;Linser r�da;15.0;kr/f�rp;product_5.jpg;end
        generateSearchText(6  , bönor              ); // ;POD;R�da b�nor;20.0;kr/f�rp;product_6.jpg;end
        generateSearchText(7  , bönor              ); // ;POD;Vita b�nor;24.0;kr/f�rp;product_7.jpg;end
        generateSearchText(8  , matbröd            ); // ;BREAD;Br�d grovt;29.0;kr/st;product_8.jpg;end
        generateSearchText(9  , rostbröd           ); // ;BREAD;Br�d vitt;28.0;kr/st;product_9.jpg;end
        generateSearchText(10 , rostbröd           ); // ;BREAD;Fralla;20.0;kr/f�rp;product_10.jpg;end
        generateSearchText(11 , knäckebröd         ); // ;BREAD;Kn�ckebr�d;24.0;kr/f�rp;product_11.jpg;end
        generateSearchText(12 , rostbröd           ); // ;BREAD;Toast;18.0;kr/f�rp;product_12.jpg;end
        generateSearchText(13 , tunnbröd           ); // ;BREAD;Tunnbr�d;20.0;kr/f�rp;product_13.jpg;end
        generateSearchText(14 , julbröd            ); // ;BREAD;V�rtlimpa;23.0;kr/st;product_14.jpg;end
        generateSearchText(15 , hallonsläktet      ); // ;BERRY;Bj�rnb�r;49.0;kr/kg;product_15.jpg;end
        generateSearchText(16 , bär                ); // ;BERRY;Bl�b�r;52.0;kr/kg;product_16.jpg;end
        generateSearchText(17 , hallonsläktet      ); // ;BERRY;Hallon;75.0;kr/kg;product_17.jpg;end
        generateSearchText(18 , vinbär             ); // ;BERRY;Krusb�r;64.0;kr/kg;product_18.jpg;end
        generateSearchText(19 , vinbär             ); // ;BERRY;R�da vinb�r;40.0;kr/kg;product_19.jpg;end
        generateSearchText(20 , hallonsläktet      ); // ;BERRY;Smultron;69.0;kr/kg;product_20.jpg;end
        generateSearchText(21 , vinbär             ); // ;BERRY;Svarta vinb�r;45.0;kr/kg;product_21.jpg;end
        generateSearchText(22 , citrusfrukter      ); // ;CITRUS_FRUIT;Apelsin;10.0;kr/kg;product_22.jpg;end
        generateSearchText(23 , citrusfrukter      ); // ;CITRUS_FRUIT;Citron;49.0;kr/kg;product_23.jpg;end
        generateSearchText(24 , citrusfrukter      ); // ;CITRUS_FRUIT;Clementin;25.0;kr/kg;product_24.jpg;end
        generateSearchText(25 , citrusfrukter      ); // ;CITRUS_FRUIT;Grapefruit;9.0;kr/kg;product_25.jpg;end
        generateSearchText(26 , citrusfrukter      ); // ;CITRUS_FRUIT;Lime;45.0;kr/kg;product_26.jpg;end
        generateSearchText(27 , cacao              ); // ;HOT_DRINKS;Cacao;18.0;kr/f�rp;product_27.jpg;end
        generateSearchText(28 , svart_te           ); // ;HOT_DRINKS;Earl grey;20.0;kr/f�rp;product_28.jpg;end
        generateSearchText(29 , grönt_te           ); // ;HOT_DRINKS;Gr�nt te;22.0;kr/f�rp;product_29.jpg;end
        generateSearchText(30 , kaffe              ); // ;HOT_DRINKS;Kaffe;48.0;kr/f�rp;product_30.jpg;end
        generateSearchText(31 , rött_te            ); // ;HOT_DRINKS;Rooibos;13.0;kr/f�rp;product_31.jpg;end
        generateSearchText(32 , juice              ); // ;COLD_DRINKS;Apelsinjuice;14.0;kr/f�rp;product_32.jpg;end
        generateSearchText(33 , läsk               ); // ;COLD_DRINKS;Cola burk;10.0;kr/st;product_33.jpg;end
        generateSearchText(34 , läsk               ); // ;COLD_DRINKS;Cola flaska;18.5;kr/st;product_34.jpg;end
        generateSearchText(35 , läsk               ); // ;COLD_DRINKS;Fanta burk;10.0;kr/st;product_35.jpg;end
        generateSearchText(36 , läsk               ); // ;COLD_DRINKS;Fanta flaska;17.0;kr/st;product_36.jpg;end
        generateSearchText(37 , soppa              ); // ;COLD_DRINKS;Fruktsoppa;10.95;kr/f�rp;product_37.jpg;end
        generateSearchText(38 , läsk               ); // ;COLD_DRINKS;7Up burk;10.0;kr/st;product_38.jpg;end
        generateSearchText(39 , läsk               ); // ;COLD_DRINKS;7Up flaska;12.4;kr/st;product_39.jpg;end
        generateSearchText(40 , vatten             ); // ;COLD_DRINKS;Vatten;12.5;kr/st;product_40.jpg;end
        generateSearchText(41 , exotisk_frukt      ); // ;EXOTIC_FRUIT;Ananas;18.0;kr/kg;product_41.jpg;end
        generateSearchText(42 , exotisk_frukt      ); // ;EXOTIC_FRUIT;Fikon;95.0;kr/kg;product_42.jpg;end
        generateSearchText(43 , exotisk_frukt      ); // ;EXOTIC_FRUIT;Granat�pple;39.0;kr/kg;product_43.jpg;end
        generateSearchText(44 , exotisk_frukt      ); // ;EXOTIC_FRUIT;Kiwi;32.0;kr/kg;product_44.jpg;end
        generateSearchText(45 , exotisk_frukt      ); // ;EXOTIC_FRUIT;Mango;14.75;kr/kg;product_45.jpg;end
        generateSearchText(46 , exotisk_frukt      ); // ;EXOTIC_FRUIT;Papaya;24.0;kr/kg;product_46.jpg;end
        generateSearchText(47 , exotisk_frukt      ); // ;EXOTIC_FRUIT;Rambutan;39.9;kr/kg;product_47.jpg;end
        generateSearchText(48 , fisk               ); // ;FISH;Fiskpinnar;35.0;kr/f�rp;product_48.jpg;end
        generateSearchText(49 , kräftdjur          ); // ;FISH;Kr�ftor;129.0;kr/kg;product_49.jpg;end
        generateSearchText(50 , fisk               ); // ;FISH;Lax;102.0;kr/kg;product_50.jpg;end
        generateSearchText(51 , kräftdjur          ); // ;FISH;R�kor;46.0;kr/kg;product_51.jpg;end
        generateSearchText(52 , fisk               ); // ;FISH;Sej;98.0;kr/kg;product_52.jpg;end
        generateSearchText(53 , fisk               ); // ;FISH;Sill;48.0;kr/kg;product_53.jpg;end
        generateSearchText(54 , fisk               ); // ;FISH;Tonfisk;14.0;kr/burk;product_54.jpg;end
        generateSearchText(55 , grönsaker          ); // ;VEGETABLE_FRUIT;Aubergine;39.95;kr/kg;product_55.jpg;end
        generateSearchText(56 , grönsaker          ); // ;VEGETABLE_FRUIT;Avocado;43.0;kr/kg;product_56.jpg;end
        generateSearchText(57 , grönsaker          ); // ;VEGETABLE_FRUIT;Gurka;34.0;kr/kg;product_57.jpg;end
        generateSearchText(58 , grönsaker          ); // ;VEGETABLE_FRUIT;Oliver;39.0;kr/kg;product_58.jpg;end
        generateSearchText(59 , grönsaker          ); // ;VEGETABLE_FRUIT;Okra;42.0;kr/kg;product_59.jpg;end
        generateSearchText(60 , grönsaker          ); // ;VEGETABLE_FRUIT;Paprika;49.0;kr/kg;product_60.jpg;end
        generateSearchText(61 , grönsaker          ); // ;VEGETABLE_FRUIT;Pumpa;29.95;kr/kg;product_61.jpg;end
        generateSearchText(149, nötfläskkötteko    ); // ;MEAT;Ekologisk blandf�rs;49.95;Kr/kg;product_149.jpg;end
        generateSearchText(150, nötfärseko         ); // ;MEAT;Ekologisk n�tf�rs;52.95;Kr/kg;product_150.jpg;end
        generateSearchText(151, sockereko          ); // ;FLOUR_SUGAR_SALT;Ekologiskt socker;20.5;Kr/f�rp;product_151.jpg;end
        generateSearchText(152, kåleko             ); // ;CABBAGE;Ekologisk blomk�l;41.35;Kr/kg;product_152.jpg;end
        generateSearchText(153, kåleko             ); // ;CABBAGE;Ekologisk brysselk�l;15.75;Kr/kg;product_153.jpg;end
        generateSearchText(154, äggeko             ); // ;DAIRIES;Ekologiska �gg;15.95;Kr/f�rp;product_154.jpg;end
    }
}
