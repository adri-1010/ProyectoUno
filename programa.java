import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dfuentes
 */
public class Programa extends javax.swing.JFrame {

    Conexion bd = new Conexion();
    Connection con = bd.conexion();

    ArrayList<String> Carros = new ArrayList<>();
    ArrayList<String> Lotes = new ArrayList<>();
    ArrayList<String> Fechas = new ArrayList<>();
    ArrayList<String> Comentarios = new ArrayList<>();

    int posicion;
    String ID_Teclado;

    public String Time(long x) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd LLLL HH:mm:ss");
        Date date = new Date(x);
        String time = formatter.format(date);

        return time;

    } //Da formato a las fechas

    public long Millis() {
        long x = System.currentTimeMillis();
        return x;
    } //Coge los milisegundos actuales (epoch)

    public void iniListas() {

        Lotes.add("");

        Comentarios.add("");

    } //Añade un espacio vacío en Lotes y Comentarios

    public void EscribeID(int y, String x) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE tunel SET idcarro=? WHERE posicion = ?");
            st.setString(1, x); //Escribe en la posición 1 el valor de x
            st.setInt(2, y); //Escribe en la posición 2 el valor de y
            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    } //Escribe en idcarro en la base de datos

    public void EscribeHist(String x, long fi) {
        int m = 0;
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO historicotunel (idmovimiento, idtunel, idcarro, lectorin) VALUES (?, 1, ?, ?)");
            st.setInt(1, 0);
            st.setString(2, x);
            st.setLong(3, fi);
            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void EscribeFecha(int y, long x) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE tunel SET fentrada=? WHERE posicion = ?");
            st.setLong(1, x); //Escribe en la posición 1 el valor de x
            st.setInt(2, y); //Escribe en la posición 2 el valor de y
            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    } //Escribe en idcarro en la base de datos

    public void Escribe() {

        String teclado = TextoEntrada.getText(); //Coge texto del cuadro de entrada
        if (teclado.equals("")) {
            System.out.println("Número de carro vacío");
        } else {
            Carros.add(teclado);
            int index = Carros.indexOf(teclado); //Cuando escribe y añade tiene en cuenta la posicion de la lista
            System.out.println(Carros);
            switch (index) {

                case 0:

                    long f0 = Millis();
                    if (ListaVacia() == true) {
                        LCarro1.setText(Carros.get(0));
                        EscribeID(1, Carros.get(0));
                        Fechas.add(Time(f0));
                        LFecha1.setText(Fechas.get(0));
                        EscribeFecha(1, f0);
                        //EscribeHist(C, f0);
                    }
                    break;

                case 1:

                    long f1 = Millis();
                    LCarro2.setText(Carros.get(1));
                    EscribeID(2, Carros.get(1));
                    Fechas.add(Time(f1));
                    LFecha2.setText(Fechas.get(1));
                    EscribeFecha(2, f1);
                    //EscribeHist(C1, f1);
                    break;

                case 2:
                    long f3 = Millis();
                    LCarro3.setText(Carros.get(2));
                    EscribeID(3, Carros.get(2));
                    Fechas.add(Time(f3));
                    LFecha3.setText(Fechas.get(2));
                    EscribeFecha(3, f3);
                    break;

                case 3:
                    long f4 = Millis();
                    LCarro4.setText(Carros.get(3));
                    EscribeID(4, Carros.get(3));
                    Fechas.add(Time(f4));
                    LFecha4.setText(Fechas.get(3));
                    EscribeFecha(4, f4);
                    break;
            }
        }
    } //Escribe en UI

    public void BorraC1() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET idcarro = 0 WHERE posicion =1");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }

        LCarro1.setText("");

    } //Borra idcarro en posicion 1

    public void BorraC2() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET idcarro = 0 WHERE posicion =2");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LCarro2.setText("");

    } //Borra idcarro en posicion 2

    public void BorraC3() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET idcarro = 0 WHERE posicion =3");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LCarro3.setText("");

    } //Borra idcarro en posicion 3

    public void BorraC4() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET idcarro = 0 WHERE posicion =4");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LCarro4.setText("");

    } //Borra idcarro en posicion 4

    public void BorraF1() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET fentrada = 0 WHERE posicion =1");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LFecha1.setText("");

    } //Borra fentrada en posicion 1

    public void BorraF2() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET fentrada = 0 WHERE posicion =2");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LFecha2.setText("");

    } //Borra fentrada en posicion 2

    public void BorraF3() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET fentrada = 0 WHERE posicion =3");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LFecha3.setText("");

    } //Borra fentrada en posicion 3

    public void BorraF4() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET fentrada = 0 WHERE posicion =4");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LFecha4.setText("");

    } //Borra fentrada en posicion 4

    public void BorraL1() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET lote = 0 WHERE posicion =1");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LLote1.setText("");

    } //Borra lote en posicion 1

    public void BorraL2() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET lote = 0 WHERE posicion =2");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LLote2.setText("");

    } //Borra lote en posicion 2

    public void BorraL3() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET lote = 0 WHERE posicion =3");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LLote3.setText("");

    } //Borra lote en posicion 3

    public void BorraL4() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET lote = 0 WHERE posicion =4");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LLote4.setText("");

    } //Borra lote en posicion 4

    public void BorraCom1() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET comentario = 0 WHERE posicion =1");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LComent1.setText("");

    } //Borra comentario en posicion 1

    public void BorraCom2() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET comentario = 0 WHERE posicion =2");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LComent2.setText("");

    } //Borra comentario en posicion 2

    public void BorraCom3() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET comentario = 0 WHERE posicion =3");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LComent3.setText("");

    } //Borra comentario en posicion 3

    public void BorraCom4() {

        try {

            PreparedStatement st = con.prepareStatement("UPDATE tunel SET comentario = 0 WHERE posicion =4");
            st.executeUpdate();

            st.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        LComent4.setText("");

    } //Borra comentario en posicion 4

    public void Botones() {
        int size = Carros.size();
        try {
            switch (size) {
                case 0:
                    Boton1.setEnabled(false);
                    Boton2.setEnabled(false);
                    Boton3.setEnabled(false);
                    Boton4.setEnabled(false);
                    break;

                case 1:
                    Boton1.setEnabled(true);
                    Boton2.setEnabled(false);
                    Boton3.setEnabled(false);
                    Boton4.setEnabled(false);
                    break;

                case 2:
                    Boton1.setEnabled(true);
                    Boton2.setEnabled(true);
                    Boton3.setEnabled(false);
                    Boton4.setEnabled(false);
                    break;

                case 3:
                    Boton1.setEnabled(true);
                    Boton2.setEnabled(true);
                    Boton3.setEnabled(true);
                    Boton4.setEnabled(false);
                    break;

                case 4:
                    Boton1.setEnabled(true);
                    Boton2.setEnabled(true);
                    Boton3.setEnabled(true);
                    Boton4.setEnabled(true);
                    break;
                default:
                    break;

            }

        } catch (Exception e) {
        }

    } //Botones deshabilitados hasta que hay datos en la fila

    public boolean ListaVacia() {
        PreparedStatement st;
        boolean b = false;
        try {
            st = con.prepareStatement("SELECT idcarro FROM tunel WHERE posicion = 1");
            ResultSet rs = st.executeQuery();

            if (rs.isBeforeFirst()) { //Si la lista esta vacia devuelve que no hay datos
                b = true;
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;

    } //Comprueba si en la BD no hay registros en idcarro

    public void Push1() {

        PreparedStatement st, ft, lt, ct;

        try {

            st = con.prepareStatement("UPDATE tunel SET idcarro=(SELECT idcarro FROM tunel WHERE posicion=2) WHERE posicion=1");
            st.executeUpdate();
            String t1 = LCarro2.getText();
            LCarro1.setText(t1);
    

            ft = con.prepareStatement("UPDATE tunel SET fentrada=(SELECT fentrada FROM tunel WHERE posicion=2) WHERE posicion=1");
            ft.executeUpdate();
            String f1 = LFecha2.getText();
            LFecha1.setText(f1);

            lt = con.prepareStatement("UPDATE tunel SET lote=(SELECT lote FROM tunel WHERE posicion=2) WHERE posicion=1");
            lt.executeUpdate();
            String l1 = LLote2.getText();
            LLote1.setText(l1);

            ct = con.prepareStatement("UPDATE tunel SET comentario=(SELECT comentario FROM tunel WHERE posicion=2) WHERE posicion=1");
            ct.executeUpdate();
            String c1 = LComent2.getText();
            LComent1.setText(c1);

            BorraC2();
            BorraL2();
            BorraF2(); //AÑADIR TAMBIEN EN EL ARRAY LA NUEVA POSICION
            BorraCom2();
            ct.close();
            lt.close();
            ft.close();
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Push2() {

        PreparedStatement st1, ft1, lt1, ct1;

        try {

            Push1();

            st1 = con.prepareStatement("UPDATE tunel SET idcarro=(SELECT idcarro FROM tunel WHERE posicion=3) WHERE posicion=2");
            st1.executeUpdate();
            String t2 = LCarro3.getText();
            LCarro2.setText(t2);

            ft1 = con.prepareStatement("UPDATE tunel SET fentrada=(SELECT fentrada FROM tunel WHERE posicion=3) WHERE posicion=2");
            ft1.executeUpdate();
            String f2 = LFecha3.getText();
            LFecha2.setText(f2);

            lt1 = con.prepareStatement("UPDATE tunel SET lote=(SELECT lote FROM tunel WHERE posicion=3) WHERE posicion=2");
            lt1.executeUpdate();
            String l2 = LLote3.getText();
            LLote2.setText(l2);

            ct1 = con.prepareStatement("UPDATE tunel SET comentario=(SELECT comentario FROM tunel WHERE posicion=3) WHERE posicion=2");
            ct1.executeUpdate();
            String c2 = LComent3.getText();
            LComent2.setText(c2);

            BorraC3();
            BorraL3();
            BorraF3();
            BorraCom3();
            ct1.close();
            lt1.close();
            ft1.close();
            st1.close();

        } catch (SQLException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Push3() {

        PreparedStatement st2, ft2, lt2, ct2;

        try {

            Push2();

            st2 = con.prepareStatement("UPDATE tunel SET idcarro=(SELECT idcarro FROM tunel WHERE posicion=4) WHERE posicion=3");
            st2.executeUpdate();
            String t3 = LCarro4.getText();
            LCarro3.setText(t3);

            ft2 = con.prepareStatement("UPDATE tunel SET fentrada=(SELECT fentrada FROM tunel WHERE posicion=4) WHERE posicion=3");
            ft2.executeUpdate();
            String f3 = LFecha4.getText();
            LFecha3.setText(f3);

            lt2 = con.prepareStatement("UPDATE tunel SET lote=(SELECT lote FROM tunel WHERE posicion=4) WHERE posicion=3");
            lt2.executeUpdate();
            String l3 = LLote4.getText();
            LLote3.setText(l3);

            ct2 = con.prepareStatement("UPDATE tunel SET comentario=(SELECT comentario FROM tunel WHERE posicion=4) WHERE posicion=3");
            ct2.executeUpdate();
            String c3 = LComent4.getText();
            LComent3.setText(c3);

            BorraC4();
            BorraL4();
            BorraF4();
            BorraCom4();
            ct2.close();
            lt2.close();
            ft2.close();
            st2.close();

        } catch (SQLException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Push() {

        switch (Carros.size()) {
            case 1:
                Push1();
                break;

            case 2:
                Push2();
                break;

            case 3:
                Push3();
                break;
            default:
                break;
        }

    } //Hace avanzar la cola en la BD

    public void ID() {

        PreparedStatement st, st1, st2, st3;
        try {
            st = con.prepareStatement("SELECT idcarro FROM tunel WHERE posicion=1");
            ResultSet rs = st.executeQuery();

            st1 = con.prepareStatement("SELECT idcarro FROM tunel WHERE posicion=2");
            ResultSet rs1 = st1.executeQuery();

            st2 = con.prepareStatement("SELECT idcarro FROM tunel WHERE posicion=3");
            ResultSet rs2 = st2.executeQuery();

            st3 = con.prepareStatement("SELECT idcarro FROM tunel WHERE posicion=4");
            ResultSet rs3 = st3.executeQuery();

            if (rs.next()) {
                String id1 = rs.getString("idcarro");
                if (!id1.equals("0")) {
                    Carros.add(0, id1);
                    LCarro1.setText(Carros.get(0));
                    System.out.println(Carros);

                    if (rs1.next()) {
                        String id2 = rs1.getString("idcarro");
                        if (!id2.equals("0")) {
                            Carros.add(1, id2);
                            LCarro2.setText(Carros.get(1));
                            System.out.println(Carros);

                            if (rs2.next()) {
                                String id3 = rs2.getString("idcarro");
                                if (!id3.equals("0")) {
                                    Carros.add(2, id3);
                                    LCarro3.setText(Carros.get(2));
                                    System.out.println(Carros);

                                    if (rs3.next()) {
                                        String id4 = rs3.getString("idcarro");
                                        if (!id4.equals("0")) {
                                            Carros.add(3, id4);
                                            LCarro4.setText(Carros.get(3));
                                            System.out.println(Carros);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Lote() {

        PreparedStatement st, st1, st2, st3;
        try {
            st = con.prepareStatement("SELECT lote FROM tunel WHERE posicion=1");
            ResultSet rs = st.executeQuery();

            st1 = con.prepareStatement("SELECT lote FROM tunel WHERE posicion=2");
            ResultSet rs1 = st1.executeQuery();

            st2 = con.prepareStatement("SELECT lote FROM tunel WHERE posicion=3");
            ResultSet rs2 = st2.executeQuery();

            st3 = con.prepareStatement("SELECT lote FROM tunel WHERE posicion=4");
            ResultSet rs3 = st3.executeQuery();

            if (rs.next()) {
                String l1 = rs.getString("lote");
                if (!l1.equals("0")) {
                    Lotes.add(0, l1);
                    LLote1.setText(Lotes.get(0));
                    System.out.println(Lotes);

                    if (rs1.next()) {
                        String l2 = rs1.getString("lote");
                        if (!l2.equals("0")) {
                            Lotes.add(1, l2);
                            LLote2.setText(Lotes.get(1));
                            System.out.println(Lotes);

                            if (rs2.next()) {
                                String l3 = rs2.getString("lote");
                                if (!l3.equals("0")) {
                                    Lotes.add(2, l3);
                                    LLote3.setText(Lotes.get(2));
                                    System.out.println(Lotes);

                                    if (rs3.next()) {
                                        String l4 = rs3.getString("lote");
                                        if (!l4.equals("0")) {
                                            Lotes.add(3, l4);
                                            LLote4.setText(Lotes.get(3));
                                            System.out.println(Lotes);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Fecha() {

        PreparedStatement st, st1, st2, st3;
        try {
            st = con.prepareStatement("SELECT fentrada FROM tunel WHERE posicion=1");
            ResultSet rs = st.executeQuery();

            st1 = con.prepareStatement("SELECT fentrada FROM tunel WHERE posicion=2");
            ResultSet rs1 = st1.executeQuery();

            st2 = con.prepareStatement("SELECT fentrada FROM tunel WHERE posicion=3");
            ResultSet rs2 = st2.executeQuery();

            st3 = con.prepareStatement("SELECT fentrada FROM tunel WHERE posicion=4");
            ResultSet rs3 = st3.executeQuery();
            long x = 0;
            if (rs.next()) {
                long f1 = rs.getLong("fentrada");
                String fe1 = Time(f1);
                if (f1 > 0) {
                    Fechas.add(0, fe1);
                    LFecha1.setText(Fechas.get(0));
                    System.out.println(Fechas);

                    if (rs1.next()) {
                        long f2 = rs1.getLong("fentrada");
                        String fe2 = Time(f2);
                        if (f2 > 0) {
                            Fechas.add(1, fe2);
                            LFecha2.setText(Fechas.get(1));
                            System.out.println(Fechas);

                            if (rs2.next()) {
                                long f3 = rs2.getLong("fentrada");
                                String fe3 = Time(f3);
                                if (f3 > 0) {
                                    Fechas.add(2, fe3);
                                    LFecha3.setText(Fechas.get(2));
                                    System.out.println(Fechas);

                                    if (rs3.next()) {
                                        long f4 = rs3.getLong("fentrada");
                                        String fe4 = Time(f4);
                                        if (f4 > 0) {
                                            Fechas.add(3, fe4);
                                            LFecha4.setText(Fechas.get(3));
                                            System.out.println(Fechas);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Comentario() {

        PreparedStatement st, st1, st2, st3;
        try {
            st = con.prepareStatement("SELECT comentario FROM tunel WHERE posicion=1");
            ResultSet rs = st.executeQuery();

            st1 = con.prepareStatement("SELECT comentario FROM tunel WHERE posicion=2");
            ResultSet rs1 = st1.executeQuery();

            st2 = con.prepareStatement("SELECT comentario FROM tunel WHERE posicion=3");
            ResultSet rs2 = st2.executeQuery();

            st3 = con.prepareStatement("SELECT comentario FROM tunel WHERE posicion=4");
            ResultSet rs3 = st3.executeQuery();

            if (rs.next()) {
                String c1 = rs.getString("comentario");
                if (!c1.equals("0")) {
                    Comentarios.add(0, c1);
                    LComent1.setText(Comentarios.get(0));
                    System.out.println(Comentarios);

                    if (rs1.next()) {
                        String c2 = rs1.getString("comentario");
                        if (!c2.equals("0")) {
                            Comentarios.add(1, c2);
                            LComent2.setText(Comentarios.get(1));
                            System.out.println(Comentarios);

                            if (rs2.next()) {
                                String c3 = rs2.getString("comentario");
                                if (!c3.equals("0")) {
                                    Comentarios.add(2, c3);
                                    LComent3.setText(Comentarios.get(2));
                                    System.out.println(Comentarios);

                                    if (rs3.next()) {
                                        String c4 = rs3.getString("comentario");
                                        if (!c4.equals("0")) {
                                            Comentarios.add(3, c4);
                                            LComent4.setText(Comentarios.get(3));
                                            System.out.println(Comentarios);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ActualizarUI() {

        ID();
        Comentario();
        Lote();
        Fecha();
    }

    public Programa() {

        initComponents();
        Botones();
        //ActualizarUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        Salida = new javax.swing.JLabel();
        Entrada = new javax.swing.JLabel();
        Comentario = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        Lote = new javax.swing.JLabel();
        Carro = new javax.swing.JLabel();
        Boton3 = new javax.swing.JButton();
        Boton4 = new javax.swing.JButton();
        Boton2 = new javax.swing.JButton();
        Boton1 = new javax.swing.JButton();
        TextoSalida = new javax.swing.JTextField();
        TextoEntrada = new javax.swing.JTextField();
        LCarro1 = new javax.swing.JLabel();
        LCarro3 = new javax.swing.JLabel();
        LCarro2 = new javax.swing.JLabel();
        LCarro4 = new javax.swing.JLabel();
        LLote3 = new javax.swing.JLabel();
        LLote2 = new javax.swing.JLabel();
        LLote4 = new javax.swing.JLabel();
        LLote1 = new javax.swing.JLabel();
        LFecha3 = new javax.swing.JLabel();
        LFecha2 = new javax.swing.JLabel();
        LFecha4 = new javax.swing.JLabel();
        LFecha1 = new javax.swing.JLabel();
        LComent3 = new javax.swing.JLabel();
        LComent2 = new javax.swing.JLabel();
        LComent4 = new javax.swing.JLabel();
        LComent1 = new javax.swing.JLabel();
        BotonSalida = new javax.swing.JButton();
        BotonEntrada = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Salida.setText("SALIDA");
        getContentPane().add(Salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 33, 45, 26));

        Entrada.setText("ENTRADA");
        getContentPane().add(Entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 33, 56, 26));

        Comentario.setText("COMENTARIOS");
        getContentPane().add(Comentario, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 134, -1, -1));

        Fecha.setText("FECHA");
        getContentPane().add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 134, -1, -1));

        Lote.setText("LOTE");
        getContentPane().add(Lote, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 134, -1, -1));

        Carro.setText("CARRO");
        getContentPane().add(Carro, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 134, -1, -1));

        Boton3.setText("Lote 3");
        Boton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton3ActionPerformed(evt);
            }
        });
        getContentPane().add(Boton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 245, -1, -1));

        Boton4.setText("Lote 4");
        Boton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton4ActionPerformed(evt);
            }
        });
        getContentPane().add(Boton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 186, -1, -1));

        Boton2.setText("Lote 2");
        Boton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton2ActionPerformed(evt);
            }
        });
        getContentPane().add(Boton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 301, -1, -1));

        Boton1.setText("Lote 1");
        Boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton1ActionPerformed(evt);
            }
        });
        getContentPane().add(Boton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        TextoSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextoSalidaActionPerformed(evt);
            }
        });
        getContentPane().add(TextoSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 65, 145, -1));

        TextoEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextoEntradaActionPerformed(evt);
            }
        });
        getContentPane().add(TextoEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(491, 65, 145, -1));
        getContentPane().add(LCarro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 360, 36, 23));
        getContentPane().add(LCarro3, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 245, 36, 23));
        getContentPane().add(LCarro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 301, 36, 23));
        getContentPane().add(LCarro4, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 186, 36, 23));
        getContentPane().add(LLote3, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 245, 36, 23));
        getContentPane().add(LLote2, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 301, 36, 23));
        getContentPane().add(LLote4, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 186, 36, 23));
        getContentPane().add(LLote1, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 360, 36, 23));
        getContentPane().add(LFecha3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 130, 23));
        getContentPane().add(LFecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 130, 23));
        getContentPane().add(LFecha4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 130, 23));
        getContentPane().add(LFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 130, 23));
        getContentPane().add(LComent3, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 245, 36, 23));
        getContentPane().add(LComent2, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 301, 36, 23));
        getContentPane().add(LComent4, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 186, 36, 23));
        getContentPane().add(LComent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 360, 36, 23));

        BotonSalida.setText("CONFIRMAR");
        BotonSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSalidaActionPerformed(evt);
            }
        });
        getContentPane().add(BotonSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 91, -1, -1));

        BotonEntrada.setText("CONFIRMAR");
        BotonEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEntradaActionPerformed(evt);
            }
        });
        getContentPane().add(BotonEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 91, -1, -1));

        pack();
    }// </editor-fold>                        

    private void TextoSalidaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void TextoEntradaActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code her e:
    }                                            

    private void Boton1ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        String[] options = {"Lote", "Comentario"};

        int x = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "OPCIONES", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (x == 0) { //Si la opcion es Lote
            String lote1 = JOptionPane.showInputDialog("Asignar numero de Lote");
            Lotes.set(0, lote1);
            String lote = Lotes.get(0);
            LLote1.setText(lote);
            try {

                PreparedStatement stlote = con.prepareStatement("UPDATE tunel SET lote=" + lote + " WHERE posicion = 1");
                stlote.executeUpdate();
                stlote.close();

            } catch (SQLException ex) {

                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);

            }
            //ActualizarLotes();
        }
        if (x == 1) { //Si la opcion es Comentario
            String coment1 = JOptionPane.showInputDialog("Asignar comentarios");
            Comentarios.set(0, coment1);
            String comentario = Comentarios.get(0);
            LComent1.setText(comentario);

            try {

                PreparedStatement stcoment = con.prepareStatement("UPDATE tunel SET comentario=" + comentario + " WHERE posicion = 1");
                stcoment.executeUpdate();
                stcoment.close();

            } catch (SQLException ex) {

                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);

            }

            //ActualizarComentarios();
        }
    }                                      

    private void Boton2ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        String[] options = {"Lote", "Comentario"};

        int x = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "OPCIONES", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (x == 0) { //Si la opcion es Lote
            String lote2 = JOptionPane.showInputDialog("Asignar numero de Lote");
            Lotes.set(1, lote2);
            String lote = Lotes.get(1);
            LLote2.setText(lote);
            try {

                PreparedStatement stlote = con.prepareStatement("UPDATE tunel SET lote=" + lote + " WHERE posicion = 2");
                stlote.executeUpdate();
                stlote.close();

            } catch (SQLException ex) {

                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);

            }
            //ActualizarLotes();
        }
        if (x == 1) { //Si la opcion es Comentario
            String coment2 = JOptionPane.showInputDialog("Asignar comentarios");
            Comentarios.set(1, coment2);
            String comentario = Comentarios.get(1);
            LComent2.setText(comentario);

            try {

                PreparedStatement stcoment = con.prepareStatement("UPDATE tunel SET comentario=" + comentario + " WHERE posicion = 2");
                stcoment.executeUpdate();
                stcoment.close();

            } catch (SQLException ex) {

                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);

            }

            //ActualizarComentarios();
        }

    }                                      

    private void Boton3ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        String[] options = {"Lote", "Comentario"};

        int x = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "OPCIONES", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (x == 0) { //Si la opcion es Lote
            String lote3 = JOptionPane.showInputDialog("Asignar numero de Lote");
            Lotes.set(2, lote3);
            String lote = Lotes.get(2);
            LLote3.setText(lote);
            try {

                PreparedStatement stlote = con.prepareStatement("UPDATE tunel SET lote=" + lote + " WHERE posicion = 3");
                stlote.executeUpdate();
                stlote.close();

            } catch (SQLException ex) {

                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);

            }
            //ActualizarLotes();
        }
        if (x == 1) { //Si la opcion es Comentario
            String coment3 = JOptionPane.showInputDialog("Asignar comentarios");
            Comentarios.set(2, coment3);
            String comentario = Comentarios.get(2);
            LComent3.setText(comentario);

            try {

                PreparedStatement stcoment = con.prepareStatement("UPDATE tunel SET comentario=" + comentario + " WHERE posicion = 3");
                stcoment.executeUpdate();
                stcoment.close();

            } catch (SQLException ex) {

                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);

            }

            //ActualizarComentarios();
        }

    }                                      

    private void Boton4ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        String[] options = {"Lote", "Comentario"};

        int x = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "OPCIONES", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (x == 0) { //Si la opcion es Lote
            String lote4 = JOptionPane.showInputDialog("Asignar numero de Lote");
            Lotes.set(3, lote4);
            String lote = Lotes.get(3);
            LLote4.setText(lote);

            try {

                PreparedStatement stlote = con.prepareStatement("UPDATE tunel SET lote=" + lote + " WHERE posicion = 4");
                stlote.executeUpdate();
                stlote.close();

            } catch (SQLException ex) {

                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);

            }
            //ActualizarLotes();
        }
        if (x == 1) { //Si la opcion es Comentario
            String coment4 = JOptionPane.showInputDialog("Asignar comentarios");
            Comentarios.set(3, coment4);
            String comentario = Comentarios.get(3);
            LComent4.setText(comentario);

            try {

                PreparedStatement stcoment = con.prepareStatement("UPDATE tunel SET comentario=" + comentario + " WHERE posicion = 4");
                stcoment.executeUpdate();
                stcoment.close();

            } catch (SQLException ex) {

                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);

            }

            //ActualizarComentarios();
        }
    }                                      

    private void BotonEntradaActionPerformed(java.awt.event.ActionEvent evt) {                                             

        Escribe();
        iniListas();
        Botones();

    }                                            

    private void BotonSalidaActionPerformed(java.awt.event.ActionEvent evt) {                                            

        String texto = TextoSalida.getText();
        int index = Carros.indexOf(texto);

        switch (index) {

            case 0:

                Carros.remove(0);
                BorraC1();

                Fechas.remove(0);
                BorraF1();

                Lotes.remove(0);
                BorraL1();

                Comentarios.remove(0);
                BorraCom1();

                Push();

                System.out.println(Carros);
                break;

            case 1:
                System.out.println("Sólo puedes dar salida al carro que ocupa la 1ª posicion");
                break;

            case 2:
                System.out.println("Sólo puedes dar salida al carro que ocupa la 1ª posicion");
                break;

            case 3:
                System.out.println("Sólo puedes dar salida al carro que ocupa la 1ª posicion");
                break;

        }
        Botones();

    }                                           

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
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Programa().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton Boton1;
    private javax.swing.JButton Boton2;
    private javax.swing.JButton Boton3;
    private javax.swing.JButton Boton4;
    private javax.swing.JButton BotonEntrada;
    private javax.swing.JButton BotonSalida;
    private javax.swing.JLabel Carro;
    private javax.swing.JLabel Comentario;
    private javax.swing.JLabel Entrada;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel LCarro1;
    private javax.swing.JLabel LCarro2;
    private javax.swing.JLabel LCarro3;
    private javax.swing.JLabel LCarro4;
    private javax.swing.JLabel LComent1;
    private javax.swing.JLabel LComent2;
    private javax.swing.JLabel LComent3;
    private javax.swing.JLabel LComent4;
    private javax.swing.JLabel LFecha1;
    private javax.swing.JLabel LFecha2;
    private javax.swing.JLabel LFecha3;
    private javax.swing.JLabel LFecha4;
    private javax.swing.JLabel LLote1;
    private javax.swing.JLabel LLote2;
    private javax.swing.JLabel LLote3;
    private javax.swing.JLabel LLote4;
    private javax.swing.JLabel Lote;
    private javax.swing.JLabel Salida;
    private javax.swing.JTextField TextoEntrada;
    private javax.swing.JTextField TextoSalida;
    // End of variables declaration                   
}
