

@Entity
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account withdrawAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account depositAccount;

    ...

    결과 : 터미널창
    Hibernate:

        alter table transaction_tb
           add constraint FKddvhoiedldmc0i8pa6juy4x69
           foreign key (deposit_account_id)
           references account_tb
    Hibernate:

        alter table transaction_tb
           add constraint FK5uok5x9akicrip538qr4x68ou
           foreign key (withdraw_account_id)
           references account_tb

----------------------------------------------------------------





