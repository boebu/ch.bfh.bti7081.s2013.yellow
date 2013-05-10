package ch.bfh.bti7081.s2013.yellow.model.generic;



import ch.bfh.bti7081.s2013.yellow.model.person.User;

import javax.persistence.*;
import java.util.Date;

/**
 * This is the superclass for all entities of the yellow-project.
 *
 * @param <T> Inherited entity class.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class YellowEntity<T>
{
    @Transient
    private final Class<T> persistentClass;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private int version;

    private Date creation;

    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "updatedBy")
    private User updatedBy;

    public YellowEntity(final Class<T> persistentClass)
    {
        this.persistentClass = persistentClass;
    }

    public boolean equals(Object obj)
    {
        if (obj != null && obj.getClass() == persistentClass)
        {
            if (((YellowEntity<?>) obj).getId() == this.id)
            {
                return true;
            }
        }
        return false;
    }

    public int hashCode()
    {
        Integer idBuf = id;
        if (idBuf != null)
        {
            return idBuf.hashCode();
        }
        else
        {
            return super.hashCode();
        }
    }

    /**
     * @return the creation
     */
    public Date getCreation()
    {
        return creation;
    }

    /**
     * @param creation
     *            the creation to set
     */
    public void setCreation(Date creation)
    {
        this.creation = creation;
    }

    /**
     * @return the lastUpdated
     */
    public Date getLastUpdated()
    {
        return lastUpdated;
    }

    /**
     * @param lastUpdated
     *            the lastUpdated to set
     */
    public void setLastUpdated(Date lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return the idACTable
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the updatedBy
     */
    public User getUpdatedBy()
    {
        return updatedBy;
    }

    /**
     * @param updatedBy
     *            the updatedBy to set
     */
    public void setUpdatedBy(User updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the version
     */
    public int getVersion()
    {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(int version)
    {
        this.version = version;
    }

}
